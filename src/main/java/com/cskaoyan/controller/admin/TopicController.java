package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.Topic;
import com.cskaoyan.bean.vo.topic.CreateTopicVO;
import com.cskaoyan.service.admin.TopicService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
     */
    @RequiresPermissions("admin:topic:list")
    @GetMapping("/list")
    public BaseRespVo list(BaseParam baseParam,String title,String subtitle){

        BaseRespData data = topicService.queryList(baseParam,title,subtitle);
        return BaseRespVo.ok(data);
    }


    /**
     * 增加新的专题
     */
    @RequiresPermissions("admin:topic:create")
     @PostMapping("create")
    public BaseRespVo create(@RequestBody Topic topic){

        Topic topicVO = topicService.createTopic(topic);
         return BaseRespVo.ok(topicVO);
     }

    /**
     * 更新专题
     */
    @RequiresPermissions("admin:topic:update")
    @PostMapping("update")
    public BaseRespVo update(@RequestBody Topic topic){

        topic = topicService.updateTopic(topic);
        return BaseRespVo.ok(topic);
    }

    /**
     * 删除专题
     */
    @RequiresPermissions("admin:topic:delete")
    @PostMapping("delete")
    public BaseRespVo delete(@RequestBody Topic topic){
        int code = topicService.deleteTopic(topic);
        if (code ==1){
            return BaseRespVo.ok();
        }else {
            return BaseRespVo.fail();
        }
    }

}
