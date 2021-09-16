package com.cskaoyan.bean.bo.cart;

import lombok.Data;

@Data
public class CheckoutBO {
    Integer cartId;
    Integer addressId;
    Integer couponId;
    Integer grouponRulesId;
}
