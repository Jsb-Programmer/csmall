package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.bo.promotion2BO.CreateTopicBO;
import com.cskaoyan.bean.vo.promotion2VO.CreateTopicVO;
import com.cskaoyan.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Topic专题模块
 */
@RestController
@RequestMapping("admin/topic")
public class TopicController {

    @Autowired
    TopicService topicService;

    /**
     * 查看全部专题信息
     * @return
     */
    @GetMapping("/list")
    public BaseRespVo list(BaseParam baseParam){

        BaseRespData data = topicService.queryList(baseParam);
        return BaseRespVo.ok(data);
    }


    /**
     * 增加新的专题
     * @return
     */
     @PostMapping("create")
    public BaseRespVo create(@RequestBody CreateTopicBO topicBO){

         CreateTopicVO topicVO = topicService.createTopic(topicBO);
         return BaseRespVo.ok(topicVO);
     }

}
