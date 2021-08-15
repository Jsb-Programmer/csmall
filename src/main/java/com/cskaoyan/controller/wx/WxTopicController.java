package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.bo.wxTopic.WxTopicBaseParam;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.wxTopic.DetailTopicVO;
import com.cskaoyan.bean.vo.wxTopic.TopicBaseRespData;
import com.cskaoyan.service.wx.WxTopicService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信Topic专题模块
 */
@RestController
@RequestMapping("wx/topic")
public class WxTopicController {

    @Autowired
    WxTopicService wxTopicService;

    /**
     * 查看全部专题信息
     */
    @RequiresPermissions("wx:topic:list")
    @GetMapping("/list")
    public BaseRespVo list(WxTopicBaseParam topicBaseParam){

        TopicBaseRespData data = wxTopicService.queryList(topicBaseParam);
        return BaseRespVo.ok(data);
    }

    /**
     * 查看专题详情
     */
    @RequiresPermissions("wx:topic:list")
    @GetMapping("/detail")
    public BaseRespVo detail(Integer id){

        DetailTopicVO data = wxTopicService.detail(id);
        return BaseRespVo.ok(data);
    }
}
