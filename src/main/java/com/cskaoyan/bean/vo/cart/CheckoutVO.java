package com.cskaoyan.bean.vo.cart;

import com.cskaoyan.bean.pojo.Address;
import com.cskaoyan.bean.pojo.Cart;

import java.util.List;

@lombok.NoArgsConstructor
@lombok.Data
public class CheckoutVO {

    @com.fasterxml.jackson.annotation.JsonProperty("grouponPrice")
    private Integer grouponPrice;
    @com.fasterxml.jackson.annotation.JsonProperty("grouponRulesId")
    private Integer grouponRulesId;
    @com.fasterxml.jackson.annotation.JsonProperty("actualPrice")
    private Double actualPrice;
    @com.fasterxml.jackson.annotation.JsonProperty("orderTotalPrice")
    private Double orderTotalPrice;
    @com.fasterxml.jackson.annotation.JsonProperty("couponPrice")
    private Double couponPrice;
    @com.fasterxml.jackson.annotation.JsonProperty("availableCouponLength")
    private Integer availableCouponLength;
    @com.fasterxml.jackson.annotation.JsonProperty("couponId")
    private Integer couponId;
    @com.fasterxml.jackson.annotation.JsonProperty("freightPrice")
    private Integer freightPrice;
    @com.fasterxml.jackson.annotation.JsonProperty("goodsTotalPrice")
    private Double goodsTotalPrice;
    @com.fasterxml.jackson.annotation.JsonProperty("addressId")
    private Integer addressId;

    private Address checkedAddress;

    private List<Cart> checkedGoodsList;
}
