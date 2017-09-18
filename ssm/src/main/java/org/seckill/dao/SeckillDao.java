package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SeckillBean;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */
public interface SeckillDao {
    int reduceNumber(long seckilled, Date killTime);
    List<SeckillBean> queryAll(@Param("offset") int offset, @Param("limit") int limit);
    SeckillBean queryById(long secillId);
}
