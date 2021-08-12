package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.bo.promotion2BO.CreateTopicBO;
import com.cskaoyan.bean.vo.promotion2VO.CreateTopicVO;

/**
 * @author yangbo
 * @description
 * @date 2021/8/12 0:08
 */
public interface TopicService {

    BaseRespData queryList(BaseParam baseParam);

    CreateTopicVO createTopic(CreateTopicBO topicBO);
}
