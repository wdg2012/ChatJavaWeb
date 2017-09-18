package org.util;

import org.springframework.util.DigestUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by Administrator on 2017/9/12.
 */
public class ServiceFilesUtils {
    private static String mNativelPath;
    private static String mFileDirName ="/uploadfile";
    private static  String mIpPath;

    public static String getIpPath() {
        return mIpPath;
    }

    public static void setIpPath(ServletRequest servletRequest) {
        if (mIpPath ==null ){
            if (servletRequest instanceof HttpServletRequest){
                HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
                mIpPath = httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName();
                if(servletRequest.getServerPort() != 80){//如果端口并非是默认80端口
                    mIpPath += ":" + servletRequest.getServerPort();

                }
                mIpPath += httpServletRequest.getContextPath()+mFileDirName+"/";
            }
        }
    }

    /**

     * 生成文件名
     * @param fileName 原文件名
     * @return 文件的名字
     */
    public static String getFileNameByMD5(String fileName){
        String result ="";
        String[] fileArr;
        String suffixName;
        String prefixName;
        try {
            fileArr= fileName.split("\\.");
            prefixName = System.currentTimeMillis()+fileArr[0];
            suffixName = "."+fileArr[1];
            result = DigestUtils.md5Digest(prefixName.getBytes())+suffixName;
        }catch (Exception e){
            e.printStackTrace();
        }


        return result;
    }

    /**
     * 获取本地路径
     * @return
     */
    public static  String getNativePath(){
        return mNativelPath;
    }

    /**
     *  设置本地文件储存路径
     * @param request 上下文 请求
     */
    public static  void setNativePath(ServletRequest request){
         if (mNativelPath ==null) {
             if (request instanceof HttpServletRequest) {
                 HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                 mNativelPath = httpServletRequest.getSession().getServletContext().getRealPath("/uploadfile");
                 File targetFile = new File(mNativelPath);
                 if (!targetFile.exists()) {
                     targetFile.mkdirs();

                 }
                 mNativelPath += "/";
             }
         }
    }
}
