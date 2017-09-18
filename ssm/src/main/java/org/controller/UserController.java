package org.controller;

import ch.qos.logback.core.rolling.helper.FileStoreUtil;
import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import org.seckill.dto.BaseDto;
import org.seckill.dto.RegisterDto;
import org.seckill.entity.AreaBean;
import org.seckill.entity.RegisterBean;

import org.seckill.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.util.ServiceFilesUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/18.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userInfoService;

    /**
     *  注册
     * @param registerBean  验证码手机号 和密码
     * @param request
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String registerUser(RegisterBean registerBean, HttpServletRequest request) throws IOException {
        String head_path ="" ;
        String json ;
        MultipartFile headFile = registerBean.getHeadPhoto();
         if (headFile!=null&&!headFile.isEmpty()){
             head_path = headFile.getOriginalFilename();
             head_path = ServiceFilesUtils.getFileNameByMD5(head_path);
         }

         if (!StringUtils.isEmpty(head_path)){
             RegisterDto dto= userInfoService.registerUser(registerBean.getPhone(), registerBean.getPassword()
                     ,registerBean.getUser_nick(),head_path,registerBean.getCountry(),headFile,request,registerBean.getVer_code());

             json = JSON.toJSONString(dto);
         }else {
             BaseDto baseDto  = new BaseDto();
             baseDto.setCode("102");
             baseDto.setError("头像文件命格式不正确");
             json = JSON.toJSONString(baseDto);
         }
        return  json ;
    }

    /**
     *  获取验证码
     * @param phone 手机号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getvercode",method = RequestMethod.POST)
    public String getVerCode(String phone){
        String json ;
       BaseDto baseDto =  userInfoService.getVerCode(phone);
       if (baseDto!=null){
           json  = JSON.toJSONString(baseDto);
       }else {
           baseDto = new BaseDto();
           baseDto.setError("获取验证码失败");
           baseDto.setCode("102");
           json = JSON.toJSONString(baseDto);
       }

        return json;
    }

    /**
     * 登录
     * @param phone 手机号
     * @param password 密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String phone,String password){
        String json ;
        BaseDto baseDto =  userInfoService.login(phone,password);
        json  = JSON.toJSONString(baseDto);
        return json;
    }

    /**
     *   更新用户签名
     * @param user_id 用户id
      * @param user_sign  用户签名
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateSign",method = RequestMethod.POST)
    public  String updateSign(String user_id , String user_sign){
        String json;
        BaseDto baseDto;
        try {
            Integer int_user_id = Integer.parseInt(user_id);
            baseDto = userInfoService.updateSign(int_user_id,user_sign);
        }catch (Exception e){
            e.printStackTrace();
            baseDto = new BaseDto();
            baseDto.setError("系统异常");
            baseDto.setCode("102");
        }

        json = JSONObject.toJSONString(baseDto);
        return  json;
    }

    /**
     *  更新用户性别
     * @param user_id 用户id
     * @param user_sex 性别
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateSex",method = RequestMethod.POST)
    public  String updateSex(String user_id , String user_sex){
        String json;
        BaseDto baseDto;
        try {
            Integer int_user_id = Integer.parseInt(user_id);
            baseDto = userInfoService.updateSex(int_user_id,user_sex);
        }catch (Exception e){
            e.printStackTrace();
            baseDto = new BaseDto();
            baseDto.setError("系统异常");
            baseDto.setCode("102");
        }

        json = JSONObject.toJSONString(baseDto);
        return  json;
    }

    /**
     * 更新聊天Id 并且只能设置一次
     * @param user_id 用户Id
     * @param chatId 聊天的名称
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateChatId",method = RequestMethod.POST)
    public  String updateChatId(String user_id , String chatId){
        String json;
        BaseDto baseDto;
        try {
            Integer int_user_id = Integer.parseInt(user_id);
            baseDto = userInfoService.updateChatId(int_user_id,chatId);
        }catch (Exception e){
            e.printStackTrace();
            baseDto = new BaseDto();
            baseDto.setError("系统异常");
            baseDto.setCode("102");
        }

        json = JSONObject.toJSONString(baseDto);
        return  json;
    }

    /**
     * 更新用户所在地
     * @param user_id 用户Id
     * @param areaBean 用户的所在地信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateArea",method = RequestMethod.POST)
    public  String updateArea(String user_id, AreaBean areaBean){
        String json;
        BaseDto baseDto;
        try {
            Integer int_user_id = Integer.parseInt(user_id);
            baseDto = userInfoService.updateArea(int_user_id,areaBean);
        }catch (Exception e){
            e.printStackTrace();
            baseDto = new BaseDto();
            baseDto.setError("系统异常");
            baseDto.setCode("102");
        }

        json = JSONObject.toJSONString(baseDto);
        return  json;
    }

    /**
     * 更新用户头像
     * @param user_id 用户id
     * @param headPhoto 用户头像
     * @param request request
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/updateHeadPhoto",method = RequestMethod.POST)
    public String updateHeadPhoto(String user_id,MultipartFile headPhoto, HttpServletRequest request) throws IOException {
        String head_path ="" ;
        BaseDto baseDto;
        MultipartFile headFile = headPhoto;
        String realPath = request.getSession().getServletContext().getRealPath("/uploadfile");
        File targetFile = new File(realPath);

        if (headFile!=null&&!headFile.isEmpty()){

            if (!targetFile.exists()){
                targetFile.mkdirs();
            }
            head_path = headFile.getOriginalFilename();

        }
        try {

            head_path = ServiceFilesUtils.getFileNameByMD5(head_path);
            Integer int_user_id = Integer.parseInt(user_id);
            if (!StringUtils.isEmpty(head_path)){
                baseDto = userInfoService.updateHeadPhoto(int_user_id,headFile,request,head_path);
            }else {
                baseDto = new BaseDto();
                baseDto.setError("头像文件格式不正确");
                baseDto.setCode("102");
            }


        }catch (Exception e){
            e.printStackTrace();
            baseDto = new BaseDto();
            baseDto.setError("系统异常");
            baseDto.setCode("102");
        }
        String json ;


        json = JSON.toJSONString(baseDto);
        return  json ;
    }

    /**
     * 获取服务器文件储存路径
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public  String updateArea(){
        String json;
        json = "ip:"+ServiceFilesUtils.getIpPath()+"本地路径："+ServiceFilesUtils.getNativePath();
        return  json;
    }

    /**
     * 验证码登录
     * @param phone 手机号
     * @param ver_code 验证码
     */
    public String smsLogin(String phone,String ver_code){
        String json="";
        userInfoService.smsLogin(phone,ver_code);
        return json;
    }
}
