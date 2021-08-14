package com.cskaoyan.mapper;

import com.cskaoyan.bean.pojo.WxOrderGoods;
import com.cskaoyan.bean.pojo.WxOrderGoodsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxOrderGoodsMapper {
    long countByExample(WxOrderGoodsExample example);

    int deleteByExample(WxOrderGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WxOrderGoods record);

    int insertSelective(WxOrderGoods record);

    List<WxOrderGoods> selectByExample(WxOrderGoodsExample example);

    WxOrderGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WxOrderGoods record, @Param("example") WxOrderGoodsExample example);

    int updateByExample(@Param("record") WxOrderGoods record, @Param("example") WxOrderGoodsExample example);

    int updateByPrimaryKeySelective(WxOrderGoods record);

    int updateByPrimaryKey(WxOrderGoods record);
}