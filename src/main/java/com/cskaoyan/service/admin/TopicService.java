package com.cskaoyan.service.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.bo.topic.CreateTopicBO;
import com.cskaoyan.bean.pojo.Topic;

/**
 * @author yangbo
 * @description
 * @date 2021/8/12 0:08
 */
public interface TopicService {

    BaseRespData queryList(BaseParam baseParam,String title,String subtitle);

    Topic createTopic(CreateTopicBO topic);

    Topic updateTopic(Topic topic);

    int deleteTopic(Topic topic);
}
