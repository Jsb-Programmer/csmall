package com.cskaoyan.bean.vo.goods;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class GoodsVO {

    /**
     * id : 1181022
     * goodsSn : 1234323
     * name : 1223
     * categoryId : 1005000
     * brandId : 1001008
     * gallery : ["http://182.92.235.201:8083/wx/storage/fetch/54y7if0bmxsujuz4kugp.jpg","http://182.92.235.201:8083/wx/storage/fetch/dnp0mwiduwz98q1pu9si.jpg"]
     * keywords : 请问
     * brief : 777A
     * isOnSale : false
     * sortOrder : 100
     * picUrl : http://182.92.235.201:8083/wx/storage/fetch/57ful3rtb7hfbj53tuih.jpg
     * isNew : false
     * isHot : true
     * unit : 个
     * counterPrice : 100
     * retailPrice : 1000
     * addTime : 2021-08-12 20:18:29
     * updateTime : 2021-08-12 20:18:29
     * deleted : false
     */

    private Integer id;
    private String goodsSn;
    private String name;
    private Integer categoryId;
    private Integer brandId;
    private String keywords;
    private String brief;
    private Boolean isOnSale;
    private Short sortOrder;
    private String picUrl;
    private Boolean isNew;
    private Boolean isHot;
    private String unit;
    private BigDecimal counterPrice;
    private BigDecimal retailPrice;
    private Date addTime;
    private Date updateTime;
    private Boolean deleted;
    private String[] gallery;
    private String detail;
}
