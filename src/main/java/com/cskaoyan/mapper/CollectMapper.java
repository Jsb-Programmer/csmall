package com.cskaoyan.mapper;

import com.cskaoyan.bean.pojo.Collect;
import com.cskaoyan.bean.pojo.CollectExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectMapper {
    long countByExample(CollectExample example);

    int deleteByExample(CollectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    List<Collect> selectByExample(CollectExample example);

    Collect selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Collect record, @Param("example") CollectExample example);

    int updateByExample(@Param("record") Collect record, @Param("example") CollectExample example);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
    //新加sql wpb
    Collect selectByValueId(Integer valueId);
    // wpb
    int updateStatusByValueId(Integer valueId);
   //wpb
    int updateStatusToZeroByValueId(Integer valueId);
//    //wpb 插入的时候识别不同用户(userId)
//    int   insertIntoCollectByUserId(Collect collect,Integer userId);
}