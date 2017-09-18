package org.seckill.service.impl;

import org.apache.commons.io.FileUtils;
import org.seckill.dao.UserDao;
import org.seckill.dto.BaseDto;
import org.seckill.dto.LoginDto;
import org.seckill.dto.RegisterDto;
import org.seckill.dto.VerCodeDto;
import org.seckill.entity.AreaBean;
import org.seckill.entity.HeadNameBean;
import org.seckill.entity.SmsBean;
import org.seckill.service.IUserService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.util.HttpUtils;
import org.util.ServiceFilesUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2017/9/3.
 */
@Service
public class UserService implements IUserService {
    private org.slf4j.Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource
    UserDao userDao;

    /**
     * 用户注册
     * @param phone 手机号
     * @param password 密码
     * @return
     */
    public RegisterDto registerUser(String phone, String password, String user_nick, String head_path, String country,
                                    MultipartFile file,
                                    HttpServletRequest request,String verCode) {
        RegisterDto registerDto =  new RegisterDto();
         String result = getRegisterResult(phone,password,user_nick,head_path,country,file,request, verCode);
         if ("注册成功".equals(result)){
             registerDto.setCode("101");
         }else {
             registerDto.setCode("102");
         }
         registerDto.setError(result);

       return registerDto;
    }

    /**
     * 获取验证码
     * @param phone 手机号
     * @return
     */
    public BaseDto getVerCode(String phone) {
        BaseDto baseDto = new BaseDto();
        VerCodeDto dto = new VerCodeDto();
      String verCode =   userDao.isExistUserOnVerCode(phone);
        Random random = new Random();
      if (verCode!=null){
          verCode = (random.nextInt(9000)+1000)+"";
          if (userDao.updateVerCode(phone,verCode)!=0){
              dto.setVer_code(verCode);
          };
      }else {
          verCode = (random.nextInt(9000)+1000)+"";
          long requestCodeTime = System.currentTimeMillis();
          if (userDao.insertVerCodeInfo(phone,verCode,requestCodeTime)!=0){
              dto.setVer_code(verCode);
          }

      }
        baseDto.setObj(dto);
      if (dto!=null){

          baseDto.setCode("101");
          baseDto.setError("获取验证码成功");
      }else {
          baseDto.setError("102");
          baseDto.setError("获取验证码失败");
      }
        return baseDto;
    }

    public BaseDto login(String phone, String password) {
       LoginDto loginDto =  userDao.checkLogin(phone);
       BaseDto baseDto = new BaseDto();
       if (loginDto!=null && loginDto.getUser_password().equals(password)){
           baseDto.setCode("101");
           baseDto.setObj(loginDto);
           baseDto.setError("登录成功");
       }else {
           baseDto.setError("密码错误或者账号不存在");
           baseDto.setCode("102");
       }
        return baseDto;
    }

    /**
     * 更新用户签名
     * @param user_id 用户id
     * @param sign 用户签名
     * @return
     */
    public BaseDto updateSign(int user_id, String sign) {
        BaseDto baseDto = new BaseDto();
        if (StringUtils.isEmpty(sign)){
           baseDto.setCode("102");
           baseDto.setError("签名不能为空");
           return baseDto;
        }
        int updateResult = userDao.updateSign(user_id,sign);
       if (updateResult!=0){
           baseDto.setCode("101");
           baseDto.setError("更新成功");
           return baseDto;
       }else {
           baseDto.setCode("102");
           baseDto.setError("更新失败");
       }

        return baseDto;
    }

    public BaseDto updateSex(int user_id, String user_sex) {
        BaseDto baseDto = new BaseDto();
        if (!StringUtils.isEmpty(user_sex)&&("男".equals(user_sex)|| "女".equals(user_sex))){
            if (userDao.updateSex(user_id,user_sex)!=0){
                baseDto.setError("更新成功");
                baseDto.setCode("101");
                return baseDto;
            }else {
                baseDto.setError("102");
                baseDto.setError("系统错误");
                return baseDto;
            }

        }else {
            baseDto.setCode("102");
            baseDto.setError("性别填写错误");
        }
        return baseDto;
    }

    /**
     *  更新聊天Id
     * @param user_id 用户Id
     * @param chatId 聊天Id
     * @return
     */
    public BaseDto updateChatId(int user_id, String chatId) {
        BaseDto baseDto = new BaseDto();
          if (!StringUtils.isEmpty(chatId)){
              if (userDao.updateChatId(user_id,chatId)!=0){
                  baseDto.setCode("101");
                  baseDto.setError("更新成功");
              }else {
                  baseDto.setError("系统错误");
                  baseDto.setCode("102");
              }

          }else {
              baseDto.setCode("102");
              baseDto.setError("chatId不能为空");
          }
        return baseDto;
    }

    /**
     *  更新用户所在地
     * @param userId
     * @param areaBean 用户所在地
     * @return
     */
    public BaseDto updateArea(int userId, AreaBean areaBean) {
        BaseDto baseDto = new BaseDto();
        if (userDao.updateArea(userId,areaBean.getUser_area(),areaBean.getUser_city(),areaBean.getCountry())!=0){
            baseDto.setCode("101");
            baseDto.setError("更新成功");

        }else {
            baseDto.setError("系统错误");
            baseDto.setCode("102");
        }
        return baseDto;
    }

    /**
     * 验证码登录
     * @param phone 手机号
     * @param code 验证码
     * @return
     */
    public BaseDto smsLogin(String phone, String code) {
     BaseDto baseDto = new BaseDto();
        LoginDto loginDto =   userDao.checkLogin(phone);
        if (loginDto!=null){
            SmsBean  smsBean= getSms(phone,code);
            if (smsBean!=null && "200".equals(smsBean.getStatus())){
                baseDto.setCode("101");
                baseDto.setObj(loginDto);
                baseDto.setError("登录成功");
            }else {
                baseDto.setCode("102");
                baseDto.setError("验证码过或者验证码错误");
            }
        }else {
            baseDto.setCode("102");
            baseDto.setError("账号未注册");
        }
        return baseDto;
    }

    /**
     * 设置用户头像
     * @param userId 用户id
     * @param headPhotoFile 头像
     * @return
     */
    public BaseDto updateHeadPhoto(int userId, MultipartFile headPhotoFile,
                                   HttpServletRequest request,String head_path) {
        BaseDto baseDto = new BaseDto();
        String rootPath = request.getScheme()+"://"+request.getServerName();
        if(request.getServerPort() != 80){//如果端口并非是默认80端口
            rootPath += ":" + request.getServerPort();
        }
        rootPath += "/uploadfile"+File.separator+head_path;
        String realPath = request.getSession().getServletContext().getRealPath("/uploadfile");
        int  reslut;
        try {
            HeadNameBean headNameBean = userDao.selectHeadName(userId);
            reslut =  userDao.updateUserHeadPhoto(userId,rootPath,head_path);
            if (reslut>0){
                FileUtils.copyInputStreamToFile(headPhotoFile.getInputStream(),new File(realPath,head_path));
                if (headNameBean!=null){
                    File olde = new File(realPath,headNameBean.getHead_name());
                    olde.delete();
                }
                baseDto.setCode("101");
                baseDto.setError("头像更新成功");
                baseDto.setObj(rootPath);
            }else {
                baseDto.setCode("102");
                baseDto.setError("系统错误");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baseDto;
    }

    /**
     * 获取注册结果
     * @param phone
     * @param password
     * @return
     */
    private String getRegisterResult(String phone, String password, String user_nick, String head_path,
                                     String country, MultipartFile file,
                                     HttpServletRequest request,String verCode){
        SmsBean smsBean = getSms(phone,verCode);
        String  rootPath = ServiceFilesUtils.getIpPath();
        rootPath += head_path;
        String realPath = ServiceFilesUtils.getNativePath();
        String reslut;

        if (!StringUtils.isEmpty(phone) && phone.length()!=11){
            reslut = "手机号格式不正确";
            return  reslut;
        }else if ((!StringUtils.isEmpty(phone) )&&
                (password.length()<6 || password.length()>16)){
            reslut ="密码长度只能是6到16位并且不能为空";
            return reslut;
        }else if (StringUtils.isEmpty(user_nick)){
            reslut = "昵称不能为空";
            return reslut;
        }else if (StringUtils.isEmpty(head_path)){
            reslut = "头像不能为空";
            return reslut;
        }else if (StringUtils.isEmpty(country)){
            reslut = "国家不能为空";
            return reslut;
        } else if (smsBean==null ||!"200".equals(smsBean.getStatus())){
            reslut =smsBean.getError();
            return reslut;
        } else if (userDao.registerUser(phone,password,rootPath,user_nick,country,head_path)==0){
            reslut="此手机号已注册过，不能再重复注册了";
            return reslut;
        }
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(realPath,head_path));
            reslut="注册成功";
        } catch (IOException e) {
            e.printStackTrace();
            reslut="文件读写异常";
        }
        return reslut;
    }

    public static SmsBean getSms(String phone, String verCode) {
        SmsBean bean =null ;
        Map<String,String>  params = new HashMap<String, String>();
        params.put("phone",phone);
        params.put("appkey","210abc1ae1e65");
        params.put("zone","86");
        params.put("code",verCode);
        try {
            bean =  HttpUtils.postAsyn("https://webapi.sms.mob.com/sms/verify",params,SmsBean.class);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return bean;
    }

}
