package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.Region;
import com.cskaoyan.service.admin.RegionService;
import com.cskaoyan.service.wx.WxRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName WxRegionController
 * @Description 地址管理 修改省市区
 * @Author wpb
 * @Date 2021/8/15 14:49
 **/
@RestController
@RequestMapping("wx/region/")
public class WxRegionController {
    @Autowired
    WxRegionService wxregionService;
    @RequestMapping("list")
    public BaseRespVo list(Integer pid){
        List<Region> data = wxregionService.updateAddressList(pid);
        return BaseRespVo.ok(data);
    }
}
