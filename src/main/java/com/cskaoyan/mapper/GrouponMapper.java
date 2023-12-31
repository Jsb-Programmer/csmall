package com.cskaoyan.mapper;


import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.pojo.Groupon;
import com.cskaoyan.bean.pojo.GrouponExample;
import com.cskaoyan.bean.pojo.GrouponRules;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GrouponMapper {
    long countByExample(GrouponExample example);

    int deleteByExample(GrouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Groupon record);

    int insertSelective(Groupon record);

    List<Groupon> selectByExample(GrouponExample example);

    Groupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Groupon record, @Param("example") GrouponExample example);

    int updateByExample(@Param("record") Groupon record, @Param("example") GrouponExample example);

    int updateByPrimaryKeySelective(Groupon record);

    int updateByPrimaryKey(Groupon record);

    int select(Groupon groupon);

    List<Integer> selectGrouponGoodsId();
}