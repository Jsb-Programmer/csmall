package com.cskaoyan.controller;

import com.cskaoyan.bean.vo.market.BaseRespVo;
import com.cskaoyan.bean.vo.market.Province;
import com.cskaoyan.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName MarketController
 * @Description 行政区域
 * @Author 王昀昊
 * @Date 2021/8/11 17:54
 * @Version 1.0
 **/
@RestController
@RequestMapping("admin/region")
public class RegionController {

    @Autowired
    RegionService regionService;

    /**
     * 回显全部行政区域
     * @return
     */
    @RequestMapping("list")
    public BaseRespVo regionList(){
        List<Province> list = regionService.regionList();
        return BaseRespVo.ok(list);
    }



}
