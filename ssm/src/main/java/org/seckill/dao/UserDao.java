package org.seckill.dao;

import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
import org.apache.ibatis.annotations.Param;
import org.seckill.dto.LoginDto;
import org.seckill.dto.VerCodeDto;
import org.seckill.entity.HeadNameBean;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/3.
 */

public interface UserDao {
    //用户注册
    int registerUser(@Param("user_phone") String phone, @Param("user_password") String password,
                     @Param("head_path") String head_path,@Param("user_nick") String user_nick,
                     @Param("country") String country,@Param("head_name") String head_name);

    //查询用户是否在验证码表里
    String isExistUserOnVerCode(@Param("user_phone") String phone);
   //当验证码表没该手机号时，插入验证码
    int insertVerCodeInfo(@Param("user_phone") String phone, @Param("ver_code") String verCode,
                             @Param("request_code_time") long requestCodeTime);
    //更新验证码
    int updateVerCode(@Param("user_phone") String phone,@Param("ver_code") String verCode);
    LoginDto checkLogin(@Param("user_phone") String user_phone);
    VerCodeDto checkVerCode(@Param("phone") String phone);

    /**
     *  更新用户个性签名
     * @param user_id 用户id
     * @param user_sign 用户的签名
     * @return
     */
    int updateSign(@Param("user_id") int user_id,@Param("user_sign") String user_sign);

    /**
     * 更新用户的性别
     * @param user_id 用户Id
     * @param sex 用户性别
     * @return
     */
    int updateSex(@Param("user_id") int user_id,@Param("user_sex") String sex);

    /**
     *  更新聊天的Id 并且只能更新一次，用户之间的聊天id不相同
     * @param user_id
     * @param user_chat_id
     * @return
     */
    int updateChatId(@Param("user_id") int user_id,@Param("user_chat_id") String user_chat_id);
    int updateArea(@Param("user_id") int user_id,@Param("user_area")
            String user_area,@Param("user_city") String user_city,
                  @Param("country") String country );

    /**
     *  g更新用户头像
     * @param user_id 用户id
     * @param head_path  头像路径
     * @return
     */
    int updateUserHeadPhoto(@Param("user_id") int user_id,@Param("head_path")
            String head_path,@Param("head_name") String head_name);
    HeadNameBean selectHeadName(@Param("user_id")int user_id);
    int selectUser(@Param("user_phone") String phone);
    String testSelect(@Param("user_id")int user_id);
}
