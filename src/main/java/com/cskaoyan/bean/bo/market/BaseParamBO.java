package com.cskaoyan.bean.bo.market;

import lombok.Data;

@Data
public class BaseParamBO {
    Integer page;
    Integer limit;
    String sort;
    String order;
}
