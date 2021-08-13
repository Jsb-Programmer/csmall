package com.cskaoyan.mapper;

import com.cskaoyan.bean.pojo.GoodsProduct;
import com.cskaoyan.bean.pojo.GoodsProductExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsProductMapper {
    long countByExample(GoodsProductExample example);

    int deleteByExample(GoodsProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsProduct record);

    int insertSelective(GoodsProduct record);

    List<GoodsProduct> selectByExample(GoodsProductExample example);

    GoodsProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsProduct record, @Param("example") GoodsProductExample example);

    int updateByExample(@Param("record") GoodsProduct record, @Param("example") GoodsProductExample example);

    int updateByPrimaryKeySelective(GoodsProduct record);

    int updateByPrimaryKey(GoodsProduct record);
}