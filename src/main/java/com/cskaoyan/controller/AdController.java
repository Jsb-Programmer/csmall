package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.Ad;
import com.cskaoyan.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mall
 * @description: 推广管理-广告管理
 * @author: Liu
 * @create: 2021-08-11 18:37
 **/

@RestController
@RequestMapping("admin/ad")
public class AdController {

    @Autowired
    AdService adService;


    // 广告首页+多条件查询
    @RequestMapping("list")
    public BaseRespVo adList(String name, String content, BaseParam baseParam) {
        BaseRespData data = adService.queryList(name, content, baseParam);
        return BaseRespVo.ok(data);
    }

    // 新增广告
    @RequestMapping("create")
    public BaseRespVo createAd (@RequestBody Ad ad) {
        Ad respAd = adService.createAd(ad);
        return BaseRespVo.ok(respAd);
    }

    // 更新广告
    @RequestMapping("update")
    public BaseRespVo updateAd (@RequestBody Ad ad) {
        Ad respAd = adService.updateAd(ad);
        return BaseRespVo.ok(respAd);
    }

    // 删除广告
    @RequestMapping("delete")
    public BaseRespVo deleteAd (@RequestBody Ad ad) {
        int affectRows = adService.deleteAd(ad);
        if (affectRows == 1) {
            return BaseRespVo.ok();
        }
        return BaseRespVo.fail("删除失败，请联系管理员");
    }





}
