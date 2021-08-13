package com.cskaoyan.bean.bo.goods;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UpdateGoodBeanBO {
    private Integer id;

    private String goodsSn;

    private String name;
    @NotNull(message = "请选择所属分类")
    private Integer categoryId;
    @NotNull(message = "请选择所属品牌商")
    private Integer brandId;

    private String[] gallery;

    private String keywords;

    private String brief;

    private Boolean isOnSale;

    private Short sortOrder;

    private String picUrl;

    private Boolean isNew;

    private Boolean isHot;

    private String unit;
    @NotNull(message = "专柜价格不能为空")
    @Digits(integer = 10, fraction = 4, message = "专柜价格要为数字")
    private String counterPrice;
    @NotNull(message = "当前价格不能为空")
    @Digits(integer = 10, fraction = 4, message = "当前价格要为数字")
    private String retailPrice;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

    private String detail;
}
