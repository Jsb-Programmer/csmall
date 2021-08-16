package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.Order;
import com.cskaoyan.bean.vo.goodsVo.RespDetailData;
import com.cskaoyan.service.admin.OrderService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @ClassName MallController
 * @Description TODO
 * @Author wpb
 * @Date 2021/8/11 19:22
 **/
@RestController
@RequestMapping("admin/order")
public class OrderController {
    /* *
    * @author:wpb
    * @Description:订单的显示与查询
    * @Date: 2021/8/11
    * @Param:
    * @return com.cskaoyan.bean.BaseRespVo
    **/
    @Autowired
    OrderService orderService;
    /**
    * @author:wpb
    * @Description: 订单列表显示
    * @Date: 2021/8/13
    * @Param: [userId, orderSn, orderStatusArray, param]
    * @return com.cskaoyan.bean.BaseRespVo
    **/
    @RequiresPermissions("admin:order:list")
    @GetMapping("list")
    public BaseRespVo list(Integer userId, String orderSn,Short [] orderStatusArray, BaseParam param){
        BaseRespData data = orderService.query(userId,orderSn, orderStatusArray, param);
        return BaseRespVo.ok(data);
    }
    /**
    * @author:Programmer
    * @Description: 订单详情
    * @Date: 2021/8/13
    * @Param: [id]
    * @return com.cskaoyan.bean.BaseRespVo
    **/
    @RequiresPermissions("admin:order:read")
    @GetMapping("detail")
    public BaseRespVo detail(Integer id){
        RespDetailData respDetailData = orderService.queryDetail(id);
        return BaseRespVo.ok(respDetailData);
    }
    // 退款
    @RequiresPermissions("admin:order:refund")
    @RequestMapping("refund")
    public BaseRespVo refund(@RequestBody Integer orderId,@RequestBody BigDecimal refundMoney){

         orderService.deleteOrder(orderId,refundMoney);
        return BaseRespVo.ok();

    }
    /**
    * @author: wpb
    * @Description:  订单发货
    * @Date: 2021/8/13
    * @Param: [order]
    * @return com.cskaoyan.bean.BaseRespVo
    **/
    @RequiresPermissions("admin:order:ship")
    @RequestMapping("ship")
    public  BaseRespVo ship(@RequestBody Order order ){
        orderService.updateStatus(order);
        return BaseRespVo.ok();
    }


}
