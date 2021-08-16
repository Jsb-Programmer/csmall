package com.cskaoyan.bean.bo.wxfootprint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-16 13:55
 **/

@NoArgsConstructor
@Data
@AllArgsConstructor
public class FootprintListBO {

    private String brief;
    private String picUrl;
    private Date addTime;
    private Integer goodsId;
    private String name;
    private Integer id;
    private BigDecimal retailPrice;
}
