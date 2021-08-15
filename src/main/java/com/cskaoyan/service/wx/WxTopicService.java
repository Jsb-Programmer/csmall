package com.cskaoyan.service.wx;

import com.cskaoyan.bean.BaseRespData2;
import com.cskaoyan.bean.WxListBaseParam2;
import com.cskaoyan.bean.vo.wxTopic.DetailTopicVO;
/**
 * @author yangbo
 * @description
 * @date 2021/8/15 14:36
 */
public interface WxTopicService {
    BaseRespData2 queryList(WxListBaseParam2 topicBaseParam);

    DetailTopicVO detail(Integer id);
}
