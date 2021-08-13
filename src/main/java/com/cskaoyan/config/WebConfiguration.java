package com.cskaoyan.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfiguration implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedMethods("*")
//                .allowedHeaders("*")
//                .allowedOrigins("*");
//                //.allowCredentials(true);
//    }
//    public Validator getValidator(){
//        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//        validator.setProviderClass(HibernateValidator.class);
//        return validator;
//    }
}
