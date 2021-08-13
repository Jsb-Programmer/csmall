package com.cskaoyan.bean.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Brand {
    private Integer id;

    private String name;

    private String desc;

    private String picUrl;

    private Byte sortOrder;

    private BigDecimal floorPrice;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;
}