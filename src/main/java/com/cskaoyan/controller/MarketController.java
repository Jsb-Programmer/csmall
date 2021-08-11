package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.market.Province;
import com.cskaoyan.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName MarketController
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/11 17:54
 * @Version 1.0
 **/
@RestController
@RequestMapping("admin")
public class MarketController {

    @Autowired
    RegionService regionService;

    @RequestMapping("region/list")
    public BaseRespVo regionList(){
        List<Province> list = regionService.regionList();

        return BaseRespVo.ok(list);
    }



}
