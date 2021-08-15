package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.BaseRespData2;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.WxListBaseParam2;
import com.cskaoyan.service.wx.WxCommentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信Comment评论模块
 */
@RestController
@RequestMapping("wx/comment")
public class WxcommentController {

    @Autowired
    WxCommentService wxCommentService;

    /**
     * 查看该id全部信息
     */
    @RequiresPermissions("wx:comment:list")
    @GetMapping("/list")
    public BaseRespVo list(Integer valueId,WxListBaseParam2 commentBaseParam,Integer type,Integer showType){

        BaseRespData2 data = wxCommentService.queryList(valueId,commentBaseParam,type,showType);
        return BaseRespVo.ok(data);
    }

}
