package com.cskaoyan.controller.test;


import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.User;
import com.cskaoyan.service.myservice.Mom;
import com.cskaoyan.service.myservice.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    MyService myService;

    @RequestMapping("/m=yTest")
    public BaseRespVo test(Mom mom){
        Integer integer = myService.find(mom.getMomId());



        return BaseRespVo.ok(integer);

    }

}
