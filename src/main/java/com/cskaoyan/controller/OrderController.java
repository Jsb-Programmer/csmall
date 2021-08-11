package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

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
    @Autowired
    OrderService orderService;
    @GetMapping("list")
    public BaseRespVo list(Integer userId, Integer orderSn, ArrayList[] orderStatusArray, BaseParam param){
        BaseRespData data = orderService.query(userId, orderSn, orderStatusArray, param);
        return BaseRespVo.ok(data);
    }
}
