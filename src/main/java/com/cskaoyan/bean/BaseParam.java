package com.cskaoyan.bean;

import lombok.Data;

@Data
public class BaseParam {
    Integer page;
    Integer limit;
    String sort;
    String order;
}
