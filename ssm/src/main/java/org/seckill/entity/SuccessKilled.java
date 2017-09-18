package org.seckill.entity;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/3.
 */
public class SuccessKilled {
    private long seckillId;
    private short state;
    private Date createTime;
    private long userPhone;
    private SeckillBean seckillBean;

    public SeckillBean getSeckillBean() {
        return seckillBean;
    }

    public void setSeckillBean(SeckillBean seckillBean) {
        this.seckillBean = seckillBean;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", createTime=" + createTime +
                ", userPhone=" + userPhone +
                '}';
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }
}
