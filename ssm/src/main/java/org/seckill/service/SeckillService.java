package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.SeckillBean;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 * 业务接口，站在使用者的角度设计接口
 * 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 */
public interface SeckillService {
    List<SeckillBean> getSkillList();
    SeckillBean getById(long seckillId);

    /**
     * 在开启输出秒杀地址，否则输出系统时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);
    SeckillExecution executeSeckill(long seckillId , long userPhone , String md5) throws SeckillException,RepeatKillException,SeckillCloseException;
}
