package com.cskaoyan.bean.vo.stat;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-12 20:42
 **/
@Data
public class RowsOrderBean implements Serializable {
    /**
     * amount : 4945
     * orders : 3
     * customers : 2
     * day : 2020-04-27
     * pcr : 2472.5
     */

    private int amount;
    private int orders;
    private int customers;
    private String day;
    private double pcr;
}
