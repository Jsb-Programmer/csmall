package com.cskaoyan.interceptor;

import com.cskaoyan.bean.pojo.Log;
import com.cskaoyan.bean.pojo.PermissionMap;
import com.cskaoyan.bean.pojo.PermissionMapExample;
import com.cskaoyan.mapper.AdminMapper;
import com.cskaoyan.mapper.LogMapper;
import com.cskaoyan.mapper.PermissionMapMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class LogInterceptor implements HandlerInterceptor {
    @Autowired
    PermissionMapMapper permissionMapMapper;
    @Autowired
    LogMapper logMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Log log = new Log();
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        String localAddr = request.getLocalAddr();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String api = method + localAddr;
        PermissionMapExample permissionMapExample = new PermissionMapExample();
        permissionMapExample.createCriteria().andApiEqualTo(api);
        List<PermissionMap> permissionMaps = permissionMapMapper.selectByExample(permissionMapExample);
        String action = null;
        if (permissionMaps.size() != 0)
            action = permissionMaps.get(0).getLabel();


    }
}
