package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.Collect;
import com.cskaoyan.bean.vo.wxCollectVo.WxCollectAddVo;
import com.cskaoyan.bean.vo.wxCollectVo.WxCollectListVo;
import com.cskaoyan.bean.vo.wxCollectVo.WxCollectVo;
import com.cskaoyan.service.wx.WxCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName WxCollectController
 * @Description TODO
 * @Author wpb
 * @Date 2021/8/15 18:16
 **/
@RestController
@RequestMapping("wx/collect")
public class WxCollectController {
    @Autowired
    WxCollectService wxCollectService;
    @RequestMapping("list")
    public BaseRespVo list(Integer type,Integer page,Integer size){
        WxCollectVo data = wxCollectService.querry(type, page, size);
        return BaseRespVo.ok(data);
    }
    // 还没测试
    @RequestMapping("addordelete")
    public BaseRespVo addordelete(@RequestBody Collect collect){
        WxCollectAddVo data = wxCollectService.addordelete(collect);
        return BaseRespVo.ok(data);
    }
}
