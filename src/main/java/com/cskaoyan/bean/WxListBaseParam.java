package com.cskaoyan.bean;

import lombok.Data;

@Data
public class WxListBaseParam extends BaseParam {
    private Boolean isHot;
    private Boolean isNew;
    private String keyword;
    private Integer size;
    private Integer categoryId;
}
