package org.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.collections.MappingChange;
import okhttp3.*;
import org.seckill.entity.SmsBean;
import org.springframework.util.StringUtils;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/9/17.
 */
public class HttpUtils {
    private static OkHttpClient okHttpClient;
    /**
     * 同步post请求
     * @param params 参数
     * @return 返回参数
     * @throws IOException
     */
    public  static <T> T postAsyn(String url,Map<String ,String>params,Class<T> t) throws Exception {
        T result;
        if (StringUtils.isEmpty(url)|| params==null){
            result = null;
            return result;
        }

        okHttpClient = getOkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        for (String key :params.keySet()){
            String value = params.get(key);
            builder.add(key,value);
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if(response.isSuccessful())
        {
            result = JSON.parseObject(response.body().string(),t);
        }else {
            result = JSON.parseObject(response.body().string(),t);
        }
        return result;
    }

    /**
     * 创建okHttp 客户端
     * @return okHTTpClient
     */
    private static OkHttpClient getOkHttpClient(){
        if (okHttpClient==null){
            ReentrantLock reentrantLock = new ReentrantLock();
            reentrantLock.lock();
            okHttpClient = new OkHttpClient();
           reentrantLock.unlock();

        }
        return okHttpClient;
    }
  public static  void main(String[]args){
      try {
          Map<String,String> params = new HashMap<String, String>();
          SmsBean result =   postAsyn("https://webapi.sms.mob.com/sms/verify",params,SmsBean.class);
        System.out.println(result);
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}
