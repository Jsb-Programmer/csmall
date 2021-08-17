package com.cskaoyan.interceptor;

import com.cskaoyan.bean.LoginUser;
import com.cskaoyan.bean.pojo.Log;
import com.cskaoyan.bean.pojo.PermissionMap;
import com.cskaoyan.bean.pojo.PermissionMapExample;
import com.cskaoyan.mapper.LogMapper;
import com.cskaoyan.mapper.PermissionMapMapper;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Configuration
public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    PermissionMapMapper permissionMapMapper;
    @Autowired
    LogMapper logMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("preHandle1");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("postHandle1");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        Log log = new Log();

        //通过request获取输入的username


        String remoteHost = request.getRemoteHost();
        String requestURI = request.getRequestURI();
        log.setIp(remoteHost);
        int status = response.getStatus();
        log.setType(1);
        log.setStatus(false);
        if (status == 200){
            log.setStatus(true);
        }
        //action 查表赋值
        String get = "GET " + requestURI;
        String post = "POST " + requestURI;
        PermissionMapExample permissionMapExample = new PermissionMapExample();
        PermissionMapExample.Criteria criteria = permissionMapExample.createCriteria();
        criteria.andApiEqualTo(get);
        List<PermissionMap> permissionMaps = permissionMapMapper.selectByExample(permissionMapExample);
        if (permissionMaps == null){
            PermissionMapExample permissionMapExample1 = new PermissionMapExample();
            permissionMapExample1.createCriteria().andApiEqualTo(post);
            permissionMaps = permissionMapMapper.selectByExample(permissionMapExample1);
        }
        String label = null;
        String permission = null;
        if ("/admin/auth/login".equals(requestURI)){
            label = "登录";
            permission = "admin:auth:login";
            log.setAdmin("匿名用户");
            log.setResult("账号或密码不正确");
            log.setStatus(false);

        }else {
            label = permissionMaps.get(0).getLabel();
            permission = permissionMaps.get(0).getPermission();

            Subject subject = SecurityUtils.getSubject();
            String username = (String) subject.getPrincipal();
            log.setAdmin(username);
            // TODO: 2021/8/17
            // TODO: 2021/8/17
            log.setResult("操作成功");
        }
        if ("/admin/auth/info".equals(requestURI)){
            label = "登录";
            permission = "admin:auth:info";
            log.setAdmin("匿名用户");
            log.setResult("");
            log.setStatus(false);

        }else {
            label = permissionMaps.get(0).getLabel();
            permission = permissionMaps.get(0).getPermission();

            Subject subject = SecurityUtils.getSubject();
            String username = (String) subject.getPrincipal();
            log.setAdmin(username);
            // TODO: 2021/8/17
            // TODO: 2021/8/17
            log.setResult("操作成功");
        }
        log.setAction(label);
        log.setComment(permission);
        log.setAddTime(new Date());
        log.setUpdateTime(new Date());
        log.setDeleted(false);

        //insert 日志
        int insert = logMapper.insert(log);
    }

}
