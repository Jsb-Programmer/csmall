package com.cskaoyan.mapper;

import com.cskaoyan.bean.pojo.Order;
import com.cskaoyan.bean.pojo.OrderExample;

import java.util.List;

import com.cskaoyan.bean.vo.goodsVo.RespDetailData;
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

    int updateByPrimaryKey(Order record);


    List<Order> selectByUserId(@Param("userId") Integer userId, @Param("orderSn") String orderSn, @Param("orderStatusArray") Integer[] orderStatusArray);

//没了
//    RespDetailData.OrderGoodsBean selectOrderGoodsJoinSpec(Integer orderId);

    Order updateStatusById(Integer orderId);

    void insertMessagebyId(@Param("orderId") Integer orderId, @Param("shipChannel") String shipChannel, @Param("shipSn") String shipSn);
}