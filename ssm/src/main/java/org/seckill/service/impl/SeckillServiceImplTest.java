package org.seckill.service.impl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.entity.SeckillBean;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-serivce.xml"})
public class SeckillServiceImplTest {
    //日志对象
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    SeckillService seckillService;
    @Test
    public void getSkillList() throws Exception {
        List<SeckillBean> list = seckillService.getSkillList();
        logger.error("list={}",list);
    }

    @Test
    public void getById() throws Exception {
        SeckillBean seckillBean = seckillService.getById(1000);
        logger.error("bean={}",seckillBean);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.error("exposer={}",exposer);
    }

    @Test
    public void executeSeckill() throws Exception {
    }
}
