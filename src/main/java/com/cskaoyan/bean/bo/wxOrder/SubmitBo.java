package com.cskaoyan.bean.bo.wxOrder;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-16 20:19
 **/

@Data
public class SubmitBo implements Serializable {

    /**
     * cartId : 0
     * addressId : 2
     * couponId : 1
     * message :
     * grouponRulesId : 0
     * grouponLinkId : 0
     */

    private int cartId;
    private int addressId;
    private int couponId;
    private String message;
    private int grouponRulesId;
    private int grouponLinkId;
}
