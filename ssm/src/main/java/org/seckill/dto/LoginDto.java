package org.seckill.dto;

/**
 * Created by Administrator on 2017/9/9.
 */
public class LoginDto {
    private String user_phone;
    private String user_password;
    private String head_path;
    private String user_nick;
    private String country;
    private Integer user_id;
    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getHead_path() {
        return head_path;
    }

    public void setHead_path(String head_path) {
        this.head_path = head_path;
    }

    public String getUser_nick() {
        return user_nick;
    }

    public void setUser_nick(String user_nick) {
        this.user_nick = user_nick;
    }

    public String getUser_country() {
        return country;
    }

    public void setUser_country(String user_country) {
        this.country = user_country;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "user_phone='" + user_phone + '\'' +
                ", user_password='" + user_password + '\'' +
                ", head_path='" + head_path + '\'' +
                ", user_nick='" + user_nick + '\'' +
                ", user_country='" + country + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
