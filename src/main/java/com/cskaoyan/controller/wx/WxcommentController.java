package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.bo.wxTopic.WxTopicBaseParam;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.wxTopic.TopicBaseRespData;
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
    public BaseRespVo list(Integer valueId, WxTopicBaseParam commentBaseParam, Integer type, Integer showType){

        TopicBaseRespData data = wxCommentService.queryList(valueId,commentBaseParam,type,showType);
        return BaseRespVo.ok(data);
    }

}
