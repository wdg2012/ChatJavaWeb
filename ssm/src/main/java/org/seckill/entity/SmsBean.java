package org.seckill.entity;

import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2017/9/17.
 */
public class SmsBean {
    private String status;
    private String error;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "SmsBean{" +
                "status='" + status + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
