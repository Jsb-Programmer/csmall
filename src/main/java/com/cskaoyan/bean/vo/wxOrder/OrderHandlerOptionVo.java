package com.cskaoyan.bean.vo.wxOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-14 16:22
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHandlerOptionVo {
    private boolean cancel;
    private boolean comment;
    private boolean confirm;
    private boolean delete;
    private boolean pay;
    private boolean rebuy;
    private boolean refund;

    //101 -> 未付款
    public static OrderHandlerOptionVo unPay(){
        return new OrderHandlerOptionVo(true, false, false,
                                 false, true, false, false);
    }

    //102 ->用户取消/系统取消
    public static OrderHandlerOptionVo userOrSystemCancel(){
        return new OrderHandlerOptionVo(false, false, false,
                true, false, false, false);
    }

    //201->已付款
    public static OrderHandlerOptionVo hadPay(){
        return new OrderHandlerOptionVo(false, false, false,
                false, false, false, true);
    }

    //202->申请退款
    public static OrderHandlerOptionVo applyRefund() {
        return new OrderHandlerOptionVo(false, false, false,
                false, false, false, false);
    }

    //203->已退款
    public static OrderHandlerOptionVo hadRefund(){
        return new OrderHandlerOptionVo(false, false, false,
                true, false, false, false);
    }

    //已发货->301
    public static OrderHandlerOptionVo hadSend(){
        return new OrderHandlerOptionVo(false, false, true,
                false, false, false, false);
    }

    //401/402用户收货/系统收货
    public static OrderHandlerOptionVo hadReceive(){
        return new OrderHandlerOptionVo(false, true, false,
                true, false, true, false);
    }


    // 已评价
    public OrderHandlerOptionVo hadComment() {
        return new OrderHandlerOptionVo(false, false, false,
                true, false, true, false);
    }
}
