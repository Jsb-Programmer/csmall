package com.cskaoyan.mapper;

import com.cskaoyan.bean.pojo.User;
import com.cskaoyan.bean.pojo.UserExample;
import com.cskaoyan.bean.vo.wxComment.ListCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    // 新加sql wpb  (String username)
    int selectIdByUsername();

    User selectByName(@Param("username") String username);

}