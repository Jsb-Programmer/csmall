package com.cskaoyan.bean.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsAttribute {
    private Integer id;

    private Integer goodsId;

    private String attribute;

    private String value;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

}