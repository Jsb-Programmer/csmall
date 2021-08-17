package com.cskaoyan.bean.bo.wxTopic;

import com.cskaoyan.bean.BaseParam;
import lombok.Data;


//专题和评论都有用到
@Data
public class WxTopicBaseParam extends BaseParam {
    private Integer page;
    private Integer size;
}
