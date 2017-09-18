package org.seckill.entity;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by Administrator on 2017/9/3.
 */
public class RegisterBean {

    private String phone;
    private String password;
    private MultipartFile headPhoto;
    private String country;
    private String user_nick;
    private String head_path;
    private String ver_code;

    public String getVer_code() {
        return ver_code;
    }

    public void setVer_code(String ver_code) {
        this.ver_code = ver_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUser_nick() {
        return user_nick;
    }

    public void setUser_nick(String user_nick) {
        this.user_nick = user_nick;
    }

    public String getHead_path() {
        return head_path;
    }

    public void setHead_path(String head_path) {
        this.head_path = head_path;
    }

    public MultipartFile getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(MultipartFile headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
