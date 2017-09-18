package org.seckill.service.impl;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.SeckillBean;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SkillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class.getSimpleName());
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;
    private String slate = "xqweqeq13123123rc1";
    public List<SeckillBean> getSkillList() {
        return seckillDao.queryAll(0,4);
    }

    public SeckillBean getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        SeckillBean seckillBean = getById(seckillId);
        if (seckillBean==null){
            return  new Exposer(false,seckillId);
        }
        Date startTime = seckillBean.getStartTime();
        Date endTime = seckillBean.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime()< startTime.getTime() ||
                nowTime.getTime()>endTime.getTime()){
            return new Exposer(false,seckillId,
                    nowTime.getTime(),
                    startTime.getTime(),
                    endTime.getTime());
        }
            String md5 = getMD5(seckillId);
        return new Exposer(true,md5,seckillId);
    }
    private  String getMD5(long seckillId){
        String base = seckillId +slate;
        base = DigestUtils.md5DigestAsHex(base.getBytes());

        return base;

    }
@Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {

        try {
            if (md5==null || !md5.equals(getMD5(seckillId))){
                throw  new SeckillException("seckill data rewrite");
            }
            //执行秒杀逻辑
            Date nowTime = new Date();
            int updateCount = seckillDao.reduceNumber(seckillId,nowTime);
            if (updateCount<=0){
                //没有更新dao操作
                throw new SeckillCloseException(" seckill is closed");
            }else {
                int insertCount = successKilledDao.insertSuccessKilled(seckillId,userPhone);
                if (insertCount<=0){
                    throw new RepeatKillException(" seckill  Repeat");
                }else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
                    return new SeckillExecution(seckillId, SkillStateEnum.SUCCESS,successKilled);
                }
            }

        }catch (SeckillCloseException e1){
            throw e1;
        }
        catch (RepeatKillException e2){
 throw e2;
        }
        catch (Exception e){
           logger.error(e.getMessage(),e);
           throw new SeckillException("seckill inner error");

        }


//        return null;
    }
}
