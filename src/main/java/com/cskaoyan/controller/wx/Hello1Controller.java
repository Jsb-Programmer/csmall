package com.cskaoyan.controller.wx;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello/hi")
public class Hello1Controller {

    @GetMapping("name")
    public String getName (String username) {
        System.out.println("进来了");
        return "你好";
    }
}
