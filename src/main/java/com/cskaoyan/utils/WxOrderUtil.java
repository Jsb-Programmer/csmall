package com.cskaoyan.utils;

import com.cskaoyan.bean.pojo.OrderGoodsExample;
import com.cskaoyan.bean.vo.wxOrder.OrderGoodsListVo;
import com.cskaoyan.bean.vo.wxOrder.OrderHandlerOptionVo;
import com.github.pagehelper.PageHelper;

import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-14 16:49
 **/

public class WxOrderUtil {
    static OrderHandlerOptionVo orderHandlerOptionVo = new OrderHandlerOptionVo();

    /**
     * 订单状态转文字
     */
    public static String statusAlter(Short before){
        if(before == 101){
            return "未付款";
        }else if(before == 102){
            return "用户取消";
        }else if(before == 103){
            return "系统取消";
        }else if(before == 201){
            return "已付款";
        }else if(before == 202){
            return "申请退款";
        }else if(before == 203){
            return "已退款";
        }else if(before == 301){
            return "已发货";
        }else if(before == 401){
            return "用户收货";
        }else if(before == 402){
            return "系统收货";
        }else if(before == 800){
            return "已评价";
        }else{
            return null;
        }
    }

    /**
     * 订单HandlerOption生成
     */
    public static OrderHandlerOptionVo checkStatus(Short status) {
        if (status == 101) {
            return orderHandlerOptionVo.unPay();
        } else if (status == 102 || status == 103) {
            return orderHandlerOptionVo.userOrSystemCancel();
        } else if (status == 201) {
            return orderHandlerOptionVo.hadPay();
        } else if (status == 202) {
            return orderHandlerOptionVo.applyRefund();
        } else if (status == 203) {
            return orderHandlerOptionVo.hadRefund();
        } else if (status == 301) {
            return orderHandlerOptionVo.hadSend();
        } else if (status == 401 || status == 402) {
            return orderHandlerOptionVo.hadReceive();
        } else if (status == 800) {
            return orderHandlerOptionVo.hadComment();
        } else {
            return null;
        }
    }


}
