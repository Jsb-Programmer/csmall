package com.cskaoyan.bean.vo.coupon;

import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-11 21:50
 **/
@Data
public class ListUserDataVo {
    private Integer id;

    private String name;

    private BaseRespVo desc;

    private String tag;

    private Integer total;

    private BigDecimal discount;

    private BigDecimal min;

    private Short limit;

    private Short type;

    private Short status;

    private Short goodsType;

    private List goodsValue;

    private Short timeType;

    private Short days;

    private Date startTime;

    private Date endTime;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;
}
