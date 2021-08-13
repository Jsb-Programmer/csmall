package com.cskaoyan.bean.bo.market;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName brandCreateBO
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/12 1:16
 * @Version 1.0
 **/
@Data
public class BrandCreateBO {

    /**
     * name : iPhone
     * desc : 不求最好，只求最贵
     * floorPrice : 250
     * picUrl : http://182.92.235.201:8083/wx/storage/fetch/rypakc0nukp11av9od5o.jpg
     */

    private String name;
    private String desc;
    private BigDecimal floorPrice;
    private String picUrl;


}
