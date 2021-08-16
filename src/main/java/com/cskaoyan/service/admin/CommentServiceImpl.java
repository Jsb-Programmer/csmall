package com.cskaoyan.service.admin;

import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.CommentParam;
import com.cskaoyan.bean.bo.comment.DeleteCommentBO;
import com.cskaoyan.bean.pojo.Comment;
import com.cskaoyan.bean.pojo.CommentExample;
import com.cskaoyan.mapper.CommentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    /**
     * 评论数据的获取
     * @param commentParam 从前端获取到的数据
     * @return 评论列表
     */
    @Transactional
    @Override
    public BaseRespData query(CommentParam commentParam) {
        PageHelper.startPage(commentParam.getPage(), commentParam.getLimit());
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        if (commentParam.getUserId() != null && commentParam.getUserId().length() != 0)
            criteria.andUserIdEqualTo(Integer.parseInt(commentParam.getUserId()));
        if (commentParam.getValueId() != null && commentParam.getValueId().length() != 0)
            criteria.andValueIdEqualTo(Integer.parseInt(commentParam.getValueId()));

        commentExample.setOrderByClause(commentParam.getSort() + " " + commentParam.getOrder());
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        long total = pageInfo.getTotal();
        return BaseRespData.create(commentList, total);
    }


    /**
     * 逻辑删除评论
     * @param deleteCommentBO 要删除的评论
     */
    @Override
    public void delete(DeleteCommentBO deleteCommentBO) {
        Comment comment = new Comment();
        comment.setId(deleteCommentBO.getId());
        comment.setDeleted(true);
        commentMapper.updateByPrimaryKeySelective(comment);
    }
}
