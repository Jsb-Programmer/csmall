package com.cskaoyan.bean;

import lombok.Data;

@Data
public class WxListBaseParam extends BaseParam {
    private String keyword;
    private Integer size;
    private Integer categoryId;
}
