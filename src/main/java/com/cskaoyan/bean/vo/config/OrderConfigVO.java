package com.cskaoyan.bean.vo.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-12 15:23
 **/
@Data
public class OrderConfigVO implements Serializable {

    /**
     * cskaoyanmall_order_unconfirm : 3
     * cskaoyanmall_order_unpaid : 34
     * cskaoyanmall_order_comment : 4
     */

    private String cskaoyanmall_order_unconfirm;
    private String cskaoyanmall_order_unpaid;
    private String cskaoyanmall_order_comment;
}
