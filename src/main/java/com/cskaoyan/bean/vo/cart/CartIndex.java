package com.cskaoyan.bean.vo.cart;

import com.cskaoyan.bean.pojo.Cart;
import lombok.Data;

import java.util.List;

@Data
public class CartIndex {
    CartTotal cartTotal;
    List<Cart> cartList;
}
