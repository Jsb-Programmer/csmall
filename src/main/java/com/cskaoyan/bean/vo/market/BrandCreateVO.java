package com.cskaoyan.bean.vo.market;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName BrandCreateVO
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/12 1:25
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
public class BrandCreateVO {
    private Integer id;
    private String name;
    private String desc;
    private String picUrl;
    private BigDecimal floorPrice;
    private Date addTime;
    private Date updateTime;
}
