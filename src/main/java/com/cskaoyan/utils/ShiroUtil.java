package com.cskaoyan.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.io.Serializable;

/**
 * 张明臣 获取shiro 中的信息
 */
public class ShiroUtil {

    /**
     * 根据传入的key 获取shiro中的对应的value
     * @param key
     * @return
     */
    public static Object getUserInfoFromShiro(Object key){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Serializable sessionId = session.getId();
        return session.getAttribute(key);
    }
}
