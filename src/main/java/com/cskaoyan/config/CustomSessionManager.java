package com.cskaoyan.config;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;


public class CustomSessionManager extends DefaultWebSessionManager {

    @Override
    protected Serializable getSessionId(ServletRequest servletRequest, ServletResponse response) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String header = request.getHeader("X-cskaoyan-mall-Admin-Token");
        if (header != null && !"".equals(header)) {
            return header;
        }
        return super.getSessionId(request, response);
    }
}
