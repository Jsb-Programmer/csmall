package com.cskaoyan.bean.bo;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.util.Date;

@Data
public class UpdateGoodsProductBO {
    private Integer id;

    private Integer goodsId;

    private String[] specifications;
    @Digits(integer = 10, fraction = 4, message = "货品售价要为数字")
    private String price;
    @Min(value = 0, message = "货品数量要为数字")
    private String number;

    private String url;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

}