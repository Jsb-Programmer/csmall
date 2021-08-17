package com.cskaoyan.bean.vo.wxTopic;

import com.cskaoyan.bean.pojo.Topic;
import lombok.Data;

@Data
public class DetailTopicVO {
    private Topic topic;
    private String[] goods;
}
