package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.Order;
import com.cskaoyan.bean.vo.goodsVo.RespDetailData;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author Programmer
 * @Date 2021/8/11 21:34
 **/
public interface OrderService {
   BaseRespData query(Integer userId, String orderSn, Short[] orderStatusArray, BaseParam param);

    RespDetailData queryDetail(Integer id);

    void deleteOrder(Integer id, BigDecimal refundMoney);

    BaseRespVo updateStatus(Order order);

}
