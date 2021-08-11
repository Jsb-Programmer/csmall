package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MallController
 * @Description TODO
 * @Author Programmer
 * @Date 2021/8/11 19:22
 **/
@RestController
@RequestMapping("admin/order")
public class OrderController {
    /* *
    * @author:wpb
    * @Description:订单的info
    * @Date: 2021/8/11
    * @Param: []
    * @return com.cskaoyan.bean.BaseRespVo
    **/
    @GetMapping("list")
    public BaseRespVo order(){
     return BaseRespVo.ok("订单页面");
    }
}
