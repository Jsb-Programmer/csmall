package com.cskaoyan.bean.vo.market;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.security.DenyAll;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName BrandUpdateVO
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/12 15:46
 * @Version 1.0
 **/
@Data
public class BrandUpdateVO {

    /**
     * id : 1046045
     * name : 小米
     * desc : 6666
     * picUrl : http://182.92.235.201:8083/wx/storage/fetch/doebyxa3t963m8qa42a6.png
     * sortOrder : 50
     * floorPrice : 10
     * addTime : 2021-08-12 15:37:28
     * updateTime : 2021-08-12 15:43:48
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
