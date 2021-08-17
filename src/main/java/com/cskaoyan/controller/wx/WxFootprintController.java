package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.bo.wxfootprint.FootprintPageBO;
import com.cskaoyan.bean.vo.market.BaseRespVo;
import com.cskaoyan.service.wx.WxFeedbackService;
import com.cskaoyan.service.wx.WxFootprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-16 13:50
 **/

@RestController
@RequestMapping("wx/footprint")
public class WxFootprintController {

    @Autowired
    WxFootprintService wxFootprintService;

    @RequestMapping("list")
    public BaseRespVo footList(Integer page,Integer size){
        FootprintPageBO pageBO = wxFootprintService.footprintList(page,size);
        return BaseRespVo.ok(pageBO);
    }

    @RequestMapping("delete")
    public BaseRespVo footprintDelete(@RequestBody Map<String,Integer> map){
        wxFootprintService.deleteFootprintById(map.get("id"));
        return BaseRespVo.ok();
    }
}
