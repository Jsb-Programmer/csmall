package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.hometest.AuthLogthChildVO;
import com.cskaoyan.bean.vo.hometest.AuthLogthVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HomeController
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/14 15:23
 * @Version 1.0
 **/
@RestController
@RequestMapping("wx/auth")
public class WXAuthController {
    @RequestMapping("login")
    public String homeIndex(){
        return "{\"errno\":0,\"data\":{\"userInfo\":{\"nickName\":\"test1\",\"avatarUrl\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80\"},\"tokenExpire\":\"2021-08-15T17:31:57.427\",\"token\":\"1eb29mil0rvkg5ml8fn8pq1ei1s65k2v\"},\"errmsg\":\"成功\"}";
    }
}
