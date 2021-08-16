package com.cskaoyan.mapper;

import com.cskaoyan.bean.pojo.PermissionMap;
import com.cskaoyan.bean.pojo.PermissionMapExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapMapper {
    long countByExample(PermissionMapExample example);

    int deleteByExample(PermissionMapExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PermissionMap record);

    int insertSelective(PermissionMap record);

    List<PermissionMap> selectByExample(PermissionMapExample example);

    PermissionMap selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PermissionMap record, @Param("example") PermissionMapExample example);

    int updateByExample(@Param("record") PermissionMap record, @Param("example") PermissionMapExample example);

    int updateByPrimaryKeySelective(PermissionMap record);

    int updateByPrimaryKey(PermissionMap record);

    String selectApiByPermission(@Param("perm") String perm);
}