package org.seckill.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.annotations.Param;
import org.seckill.dto.BaseDto;
import org.seckill.dto.LoginDto;
import org.seckill.dto.RegisterDto;
import org.seckill.entity.AreaBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/9/3.
 */
public interface IUserService {
    RegisterDto registerUser(String phone , String password, String user_nick, String head_path, String country,
                             MultipartFile file, HttpServletRequest request,String verCode);
    BaseDto getVerCode(String phone);
    BaseDto login(String phone,String password);
    BaseDto updateSign(int user_id,String sign);
    BaseDto updateSex(int user_id,String user_sex);
    BaseDto updateChatId(int user_id,String chatId);
    BaseDto updateArea(int userId, AreaBean areaBean);
    BaseDto smsLogin(String phone,String code);
    /**
     * 设置用户头像
     * @param userId 用户id
     * @param headPhotoFile 头像
     * @return
     */
    BaseDto updateHeadPhoto(int userId,MultipartFile headPhotoFile,
                            HttpServletRequest request,String head_path);
}
