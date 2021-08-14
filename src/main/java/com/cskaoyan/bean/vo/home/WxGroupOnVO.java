package com.cskaoyan.bean.vo.home;

import com.cskaoyan.bean.pojo.Goods;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WxGroupOnVO {
    private BigDecimal groupon_price;
    private Goods goods;
    private Integer groupon_member;
}
