package com.cskaoyan.service.wx;

import com.cskaoyan.bean.bo.wxOrder.OrderCommentBo;
import com.cskaoyan.bean.bo.wxOrder.WxOrderBaseParamBO;
import com.cskaoyan.bean.pojo.Order;
import com.cskaoyan.bean.pojo.WxOrderGoods;
import com.cskaoyan.bean.vo.wxOrder.OrderDetailDataVo;
import com.cskaoyan.bean.vo.wxOrder.WxOrderBaseRespVo;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-14 14:24
 **/
public interface WxOrderService {
    WxOrderBaseRespVo getOrderList(Integer showType, WxOrderBaseParamBO baseParamBO);

    OrderDetailDataVo getOrderDetail(Integer orderId);

    int cancelOrder(Order order);

    int refundOrder(Order order);

    int deleteOrder(Order order);

    WxOrderGoods goods(Integer orderId, Integer goodsId);

    void comment(OrderCommentBo orderCommentBo);

    void confirm(Order order);
}
