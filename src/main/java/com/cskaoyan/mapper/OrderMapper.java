package com.cskaoyan.mapper;

import com.cskaoyan.bean.bo.cart.Index;
import com.cskaoyan.bean.pojo.Order;
import com.cskaoyan.bean.pojo.OrderExample;

import java.util.List;
import java.util.Map;


import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);
    //new sql admin/order/ship by wpb
    int updateByPrimaryKeySelectiveNew(Order record);

    int updateByPrimaryKey(Order record);


//1
    List<Order> selectByUserId(@Param("userId") Integer userId, @Param("orderSn") String orderSn, @Param("orderStatusArray") Integer[] orderStatusArray);

//没了
//    RespDetailData.OrderGoodsBean selectOrderGoodsJoinSpec(Integer orderId);
// 2
    Order updateStatusById(Integer orderId);
//3
    void insertMessagebyId(@Param("orderId") Integer orderId, @Param("shipChannel") String shipChannel, @Param("shipSn") String shipSn);

    List<Index> selectOrderStatus(Integer userId);
}