package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    /**
     * 商品列表
     * @param goodsSn 商品编号
     * @param name  商品名称
     * @param baseParam
     * @return
     */
    @GetMapping("list")
    public BaseRespVo list(String goodsSn, String name, BaseParam baseParam) {
        BaseRespData baseRespData = goodsService.query(goodsSn, name, baseParam);
        return BaseRespVo.ok(baseRespData);
    }
}
