package com.cskaoyan.anno;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@interface RountingInjected {
    String value() default "helloServiceImpl1";
}
