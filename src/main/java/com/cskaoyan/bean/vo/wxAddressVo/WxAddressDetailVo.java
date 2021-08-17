package com.cskaoyan.bean.vo.wxAddressVo;

import lombok.Data;

/**
 * @ClassName WxAddressDetailVo
 * @Description TODO
 * @Author wpb
 * @Date 2021/8/15 1:19
 **/
@Data
public class WxAddressDetailVo {

    /**
     * isDefault : false
     * areaId : 376
     * address : bushui
     * cityName : 市辖区
     * areaName : 东城区
     * name : qwe
     * mobile : 13812341234
     * id : 26
     * cityId : 32
     * provinceName : 北京市
     * provinceId : 1
     */

    private Boolean isDefault;
    private int areaId;
    private String address;
    private String cityName;
    private String areaName;
    private String name;
    private String mobile;
    private int id;
    private int cityId;
    private String provinceName;
    private int provinceId;


}
