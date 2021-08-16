package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.pojo.Feedback;
import com.cskaoyan.bean.vo.market.BaseRespVo;
import com.cskaoyan.service.wx.WxFeedbackService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-16 11:33
 **/

@RestController
@RequestMapping("wx/feedback")
public class WxFeedbackController {

    @Autowired
    WxFeedbackService wxFeedbackService;

    @RequestMapping("submit")
    public BaseRespVo feedback(@RequestBody Feedback feedback){
        int code = wxFeedbackService.addFeedback(feedback);
        if (code == 400){
            return BaseRespVo.fail(603,"反馈失败");
        }
        return BaseRespVo.ok();
    }
}
