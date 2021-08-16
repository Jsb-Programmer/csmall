package com.cskaoyan.mapper;

import com.cskaoyan.bean.pojo.KeyFestival;
import com.cskaoyan.bean.pojo.KeyFestivalExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KeyFestivalMapper {
    long countByExample(KeyFestivalExample example);

    int deleteByExample(KeyFestivalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(KeyFestival record);

    int insertSelective(KeyFestival record);

    List<KeyFestival> selectByExample(KeyFestivalExample example);

    KeyFestival selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") KeyFestival record, @Param("example") KeyFestivalExample example);

    int updateByExample(@Param("record") KeyFestival record, @Param("example") KeyFestivalExample example);

    int updateByPrimaryKeySelective(KeyFestival record);

    int updateByPrimaryKey(KeyFestival record);

    KeyFestival selectByTime(int time);
}