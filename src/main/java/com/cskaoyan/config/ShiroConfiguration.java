package com.cskaoyan.config;

import com.cskaoyan.realm.AdminRealm;
import com.cskaoyan.realm.WxRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;

//@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        //factoryBean.setLoginUrl();

        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/hello", "anon");
        filterMap.put("/admin/auth/login", "anon");      //通常登录请求设置为匿名请求
        filterMap.put("/admin/dashboard", "authc");
        filterMap.put("/admin/auth/info", "anon");
        //====
        filterMap.put("/wx/auth/login","anon");
        filterMap.put("/wx/auth/logout","anon");
        filterMap.put("/wx/user/index","anon");

        filterMap.put("/wx/catalog/**","anon");
        filterMap.put("/**", "authc");        //访问请求，先要执行authc的filter，判断是否是认证状态
        factoryBean.setFilterChainDefinitionMap(filterMap);
        return factoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(
                                                     CustomSessionManager customSessionManager,
                                                     CustomAuthenticator authenticator) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //给默认的认证器和授权器提供realm
        securityManager.setSessionManager(customSessionManager);
        securityManager.setAuthenticator(authenticator);
        return securityManager;
    }

    //整合商城应用，用于跨域处理
    //sessionManager
    @Bean
    public CustomSessionManager sessionManager() {
        CustomSessionManager customSessionManager = new CustomSessionManager();
        customSessionManager.setGlobalSessionTimeout(1800000);
        customSessionManager.setDeleteInvalidSessions(true);
        return customSessionManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public CustomAuthenticator authenticator(AdminRealm adminRealm, WxRealm wxRealm){
        CustomAuthenticator customAuthenticator = new CustomAuthenticator();
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(adminRealm);
        realms.add(wxRealm);
        customAuthenticator.setRealms(realms);
        return customAuthenticator;
    }
}
