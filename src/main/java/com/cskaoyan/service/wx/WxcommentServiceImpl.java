package com.cskaoyan.service.wx;

import com.cskaoyan.bean.bo.wxTopic.WxTopicBaseParam;
import com.cskaoyan.bean.pojo.Comment;
import com.cskaoyan.bean.pojo.CommentExample;
import com.cskaoyan.bean.pojo.User;
import com.cskaoyan.bean.vo.wxComment.CountCommentVO;
import com.cskaoyan.bean.vo.wxComment.ListCommentVO;
import com.cskaoyan.bean.vo.wxTopic.TopicBaseRespData;
import com.cskaoyan.mapper.CommentMapper;
import com.cskaoyan.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangbo
 * @description
 * @date 2021/8/15 17:44
 */
@Service
public class WxcommentServiceImpl implements WxCommentService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public TopicBaseRespData queryList(Integer valueId, WxTopicBaseParam commentBaseParam, Integer type, Integer showType) {
        PageHelper.startPage(commentBaseParam.getPage(), commentBaseParam.getSize());

        CommentExample example = new CommentExample();

        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andValueIdEqualTo(valueId);
        criteria.andDeletedEqualTo(false);
        List<Comment> commentList = commentMapper.selectByExample(example);

        ArrayList<ListCommentVO> commentVOS = new ArrayList<>();
        for (Comment comment : commentList) {
            ListCommentVO commentVO = new ListCommentVO();

            ListCommentVO.UserInfoBean userInfoBean = new ListCommentVO.UserInfoBean();
            User user = userMapper.selectByPrimaryKey(comment.getUserId());
            userInfoBean.setNickName(user.getNickname());
            userInfoBean.setAvatarUrl(user.getAvatar());

            commentVO.setUserInfo(userInfoBean);
            commentVO.setContent(comment.getContent());
            commentVO.setAddTime(comment.getAddTime());
            commentVO.setPicList(comment.getPicUrls());
            commentVOS.add(commentVO);
        }

        //可以获得分页信息,在PageInfo中放入查询结果
        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        long total = pageInfo.getTotal();//select count(*) 根据上面执行的查询拼接后面的

        return TopicBaseRespData.create(commentVOS, total, commentBaseParam.getPage());
    }

    @Override
    public CountCommentVO queryCount(Integer valueId, Integer type) {

        CountCommentVO commentVO = new CountCommentVO();

        CommentExample example = new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andValueIdEqualTo(valueId);
        criteria.andDeletedEqualTo(false);
        commentVO.setAllCount(commentMapper.countByExample(example));

        example = new CommentExample();
        CommentExample.Criteria criteria1 = example.createCriteria();
        criteria1.andValueIdEqualTo(valueId);
        criteria1.andDeletedEqualTo(false);
        criteria1.andHasPictureEqualTo(true);
        commentVO.setHasPicCount(commentMapper.countByExample(example));

        return commentVO;
    }
}

