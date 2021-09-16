package com.cskaoyan.mapper;

import com.cskaoyan.bean.pojo.Comment;
import com.cskaoyan.bean.pojo.CommentExample;
import com.cskaoyan.bean.vo.goods.CommentDataVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    long countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<CommentDataVO> selectCommentWithUserByGoodsId(Integer id);
}