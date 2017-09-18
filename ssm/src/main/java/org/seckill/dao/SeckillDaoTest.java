package org.seckill.dao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SeckillBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/7/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    @Resource
    private SeckillDao seckillDao;
    @Test
    public void reduceNumber() throws Exception {
    }

    @Test
    public void queryAll() throws Exception {

        List<SeckillBean> seckills=seckillDao.queryAll(0,100);
        for (SeckillBean seckill : seckills)
        {
            System.out.println(seckill);
        }

    }

    @Test
    public void queryById() throws Exception {
        long seckillId=1000;
        SeckillBean seckill=seckillDao.queryById(seckillId);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }


}
