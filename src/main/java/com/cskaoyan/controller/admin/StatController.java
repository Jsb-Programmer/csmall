package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.stat.StatGoodsVO;
import com.cskaoyan.bean.vo.stat.StatOrderVO;
import com.cskaoyan.bean.vo.stat.StatUserVO;
import com.cskaoyan.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-12 18:44
 **/

@RestController
@RequestMapping("admin/stat")
public class StatController {

    @Autowired
    StatService statService;

    /**
     * 用户统计
     * @return
     */
    @RequestMapping("user")
    public BaseRespVo statUser() {
        StatUserVO statUserVO = statService.statUser();
        return BaseRespVo.ok(statUserVO);
    }

    /**
     * 订单统计
     * @return
     */
    @RequestMapping("order")
    public BaseRespVo statOrder() {
        StatOrderVO statOrderVO = statService.statOrder();
        return BaseRespVo.ok(statOrderVO);
    }

    /**
     * 商品统计
     * @return
     */
    @RequestMapping("goods")
    public BaseRespVo statGoods() {
        StatGoodsVO statGoodsVO = statService.statGoods();
        return BaseRespVo.ok(statGoodsVO);
    }


}
