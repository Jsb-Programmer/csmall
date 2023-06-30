package com.cskaoyan.config;

import com.cskaoyan.interceptor.LogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@ComponentScan("com.cskaoyan.controller")
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {



    @Bean
    public LogInterceptor logInterceptor () {
        return new LogInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //HandlerInterceptor是谁、作用范围、
        InterceptorRegistration registration = registry.addInterceptor(logInterceptor()).addPathPatterns("/**");

    }

}
