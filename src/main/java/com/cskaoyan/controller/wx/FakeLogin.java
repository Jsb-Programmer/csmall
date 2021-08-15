package com.cskaoyan.controller.wx;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeLogin {

    @RequestMapping("wx/auth/login")
    public String login(){
        return "{\"errno\":0,\"data\":{\"userInfo\":{\"nickName\":\"test1\",\"avatarUrl\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80\"},\"tokenExpire\":\"2021-08-15T16:43:50.735\",\"token\":\"vmt1ddrccsrq5v7v1ckuuy8tqwdc6jh2\"},\"errmsg\":\"成功\"}";
    }
}
