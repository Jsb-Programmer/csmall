package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.config.ExpressConfigVO;
import com.cskaoyan.bean.vo.config.MallConfigVO;
import com.cskaoyan.bean.vo.config.OrderConfigVO;
import com.cskaoyan.bean.vo.config.WxConfigVO;
import com.cskaoyan.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: mall
 * @description: 配置管理
 * @author: Liu
 * @create: 2021-08-12 15:13
 **/

@RestController
@RequestMapping("admin/config")
public class ConfigController {

    @Autowired
    ConfigService configService;

    /**
     * 商场配置回显(GET)
     */
    @GetMapping("mall")
    public BaseRespVo configMallGet() {
        MallConfigVO mallConfigVO = configService.getMallConfig();
        return BaseRespVo.ok(mallConfigVO);
    }

    /**
     * 商场配置提交(POST)
     */
    @PostMapping("mall")
    public BaseRespVo configMallPOST(@RequestBody MallConfigVO mallConfigVO) {
        int affectRows = configService.postMallConfig(mallConfigVO);
        if (affectRows == 4) {
            return BaseRespVo.ok();
        }
        return BaseRespVo.fail("提交失败，请重试");
    }

    /**
     * 运费配置回显(GET)
     */
    @GetMapping("express")
    public BaseRespVo configExpressGet() {
        ExpressConfigVO expressConfigVO = configService.getExpressConfig();
        return BaseRespVo.ok(expressConfigVO);
    }

    /**
     * 运费配置提交(POST)
     */
    @PostMapping("express")
    public BaseRespVo configExpressPOST(@RequestBody ExpressConfigVO expressConfigVO) {
        int affectRows = configService.postExpressConfig(expressConfigVO);
        if (affectRows == 2) {
            return BaseRespVo.ok();
        }
        return BaseRespVo.fail("提交失败，请重试");
    }

    /**
     * 订单配置回显(GET)
     */
    @GetMapping("order")
    public BaseRespVo configOrderGet() {
        OrderConfigVO orderConfigVO = configService.getOrderConfig();
        return BaseRespVo.ok(orderConfigVO);
    }

    /**
     * 订单配置提交(POST)
     */
    @PostMapping("order")
    public BaseRespVo configOrderPOST(@RequestBody OrderConfigVO orderConfigVO) {
        int affectRows = configService.postOrderConfig(orderConfigVO);
        if (affectRows == 3) {
            return BaseRespVo.ok();
        }
        return BaseRespVo.fail("提交失败，请重试");
    }

    /**
     * 小程序配置回显(GET)
     */
    @GetMapping("wx")
    public BaseRespVo configWxGet() {
        WxConfigVO wxConfigVO = configService.getWxConfig();
        return BaseRespVo.ok(wxConfigVO);
    }

    /**
     * 小程序配置提交(POST)
     */
    @PostMapping("wx")
    public BaseRespVo configWxPOST(@RequestBody WxConfigVO wxConfigVO) {
        int affectRows = configService.postWxConfig(wxConfigVO);
        if (affectRows == 7) {
            return BaseRespVo.ok();
        }
        return BaseRespVo.fail("提交失败，请重试");
    }

}
