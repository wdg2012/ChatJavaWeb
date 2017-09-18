package org.seckill.entity;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/3.
 */
public class SeckillBean {
    private String name;
    private int number;
    private Date startTime;
    private Date endTime;
    private Date creteTime;

    @Override
    public String toString() {
        return "SeckillBean{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", creteTime=" + creteTime +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreteTime() {
        return creteTime;
    }

    public void setCreteTime(Date creteTime) {
        this.creteTime = creteTime;
    }
}
