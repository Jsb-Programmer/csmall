package com.cskaoyan.bean.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class WxOrderGoods {
    private Integer id;

    private Integer orderId;

    private Integer goodsId;

    private String goodsName;

    private String goodsSn;

    private Integer productId;

    private Short number;

    private BigDecimal price;

    private String[] specifications;

    private String picUrl;

    private Integer comment;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

}