package com.cskaoyan.service.wx;

import com.cskaoyan.bean.bo.wxTopic.WxTopicBaseParam;
import com.cskaoyan.bean.vo.wxTopic.DetailTopicVO;
import com.cskaoyan.bean.vo.wxTopic.TopicBaseRespData;

/**
 * @author yangbo
 * @description
 * @date 2021/8/15 14:36
 */
public interface WxTopicService {
    TopicBaseRespData queryList(WxTopicBaseParam topicBaseParam);

    DetailTopicVO detail(Integer id);
}
