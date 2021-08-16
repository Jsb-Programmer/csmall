package com.cskaoyan.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtil {
    public int getUserId(){
        Subject subject = SecurityUtils.getSubject();
        int principal = ((Integer) subject.getPrincipal());
        return principal;
    }
}
