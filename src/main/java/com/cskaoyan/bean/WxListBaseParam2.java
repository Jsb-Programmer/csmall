package com.cskaoyan.bean;

import lombok.Data;


//专题和评论都有用到
@Data
public class WxListBaseParam2 extends BaseParam{
    private Integer page;
    private Integer size;
}
