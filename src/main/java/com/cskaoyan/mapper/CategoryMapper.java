package com.cskaoyan.mapper;

import com.cskaoyan.bean.pojo.Category;
import com.cskaoyan.bean.pojo.CategoryExample;
import com.cskaoyan.bean.vo.market.CategoryL1VO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<CategoryL1VO> selectALL();

    Category selectById(Integer id);

    int selectToMinID();

    List<Category> selectToAllL1();
}