package com.cskaoyan.bean.vo.stat;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-12 20:41
 **/
@Data
public class RowsGoodsBean implements Serializable {
    /**
     * amount : 10319.0
     * orders : 20
     * day : 2020-04-27
     * products : 20
     */

    private double amount;
    private int orders;
    private String day;
    private int products;
}