package com.cskaoyan.bean.bo.market;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName BrandUpdateBO
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/12 15:42
 * @Version 1.0
 **/
@Data
public class BrandUpdateBO {

    /**
     * id : 1046010
     * name : IG
     * desc : 冠军
     * picUrl :
     * sortOrder : 51
     * floorPrice : 10
     * addTime : 2021-08-12
     * updateTime : 2021-08-12
     * deleted : false
     */

    private int id;
    private String name;
    private String desc;
    private String picUrl;
    private byte sortOrder;
    private BigDecimal floorPrice;
    private Date addTime;
    private Date updateTime;
    private Boolean deleted;
}
