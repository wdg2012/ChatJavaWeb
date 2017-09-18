package org.listener;

import org.util.ServiceFilesUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Created by Administrator on 2017/9/15.
 */
public class RequestListener implements ServletRequestListener {
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServiceFilesUtils.setIpPath(servletRequestEvent.getServletRequest());
        ServiceFilesUtils.setNativePath(servletRequestEvent.getServletRequest());


    }
}
