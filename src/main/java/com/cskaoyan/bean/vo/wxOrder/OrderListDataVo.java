package com.cskaoyan.bean.vo.wxOrder;

import com.cskaoyan.bean.pojo.OrderGoods;
import com.cskaoyan.bean.pojo.WxOrderGoods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-14 16:31
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListDataVo {
    private String orderStatusText;
    private boolean isGroupin;
    private String orderSn;
    private BigDecimal actualPrice;
    private int id;
    private OrderHandlerOptionVo handleOption;
    private List<WxOrderGoods> goodsList;
}
