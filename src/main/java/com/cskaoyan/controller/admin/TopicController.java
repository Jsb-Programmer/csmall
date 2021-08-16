package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.bo.topic.CreateTopicBO;
import com.cskaoyan.bean.pojo.Topic;
import com.cskaoyan.bean.vo.topic.CreateTopicVO;
import com.cskaoyan.service.admin.TopicService;
import com.cskaoyan.utils.ValidationUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
     * 查看专题信息
     */
    @RequiresPermissions("admin:topic:list")
    @GetMapping("/list")
    public BaseRespVo list(BaseParam baseParam, String title, String subtitle) {

        BaseRespData data = topicService.queryList(baseParam, title, subtitle);

        if (data == null) {
            return BaseRespVo.fail("没有相关专题内容");
        } else {
            return BaseRespVo.ok(data);
        }

    }


    /**
     * 增加新的专题
     */
    @RequiresPermissions("admin:topic:create")
    @PostMapping("create")
    public BaseRespVo create(@RequestBody @Validated CreateTopicBO topic, BindingResult bindingResult) {
        if(ValidationUtil.dealWithFieldError(bindingResult)!= null){
            throw new NumberFormatException("参数不正确");
        }
        Topic topicVO = topicService.createTopic(topic);
        if (topicVO != null) {
            return BaseRespVo.ok(topicVO);
        }
        return BaseRespVo.fail("添加失败惹~");
    }

    /**
     * 更新专题
     */
    @RequiresPermissions("admin:topic:update")
    @PostMapping("update")
    public BaseRespVo update(@RequestBody Topic topic) {

        topic = topicService.updateTopic(topic);
        return BaseRespVo.ok(topic);
    }

    /**
     * 删除专题
     */
    @RequiresPermissions("admin:topic:delete")
    @PostMapping("delete")
    public BaseRespVo delete(@RequestBody Topic topic) {
        int code = topicService.deleteTopic(topic);
        if (code == 1) {
            return BaseRespVo.ok();
        } else {
            return BaseRespVo.fail("当前专题不可删");
        }
    }

}
