package com.cskaoyan.service.wx;

import com.cskaoyan.bean.bo.wxTopic.WxTopicBaseParam;
import com.cskaoyan.bean.vo.wxComment.CountCommentVO;
import com.cskaoyan.bean.vo.wxTopic.TopicBaseRespData;

/**
 * @author yangbo
 * @description
 * @date 2021/8/15 17:43
 */
public interface WxCommentService {

    TopicBaseRespData queryList(Integer valueId, WxTopicBaseParam commentBaseParam, Integer type, Integer showType);

    CountCommentVO queryCount(Integer valueId, Integer type);
}
