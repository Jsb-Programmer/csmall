package com.cskaoyan.mapper;

import com.cskaoyan.bean.bo.cart.AddBO;
import com.cskaoyan.bean.pojo.Cart;
import com.cskaoyan.bean.pojo.CartExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CartMapper {
    long countByExample(CartExample example);

    int deleteByExample(CartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    List<Cart> selectByExample(CartExample example);

    Cart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    List<Integer> selectGrouponGoodsId(Integer userId);

    Cart selectCartNewest(Integer userId);

    int updateNumber(@Param("number") Integer number, @Param("userId") Integer userId, @Param("addBO") AddBO addBO, @Param("date") Date date);
}