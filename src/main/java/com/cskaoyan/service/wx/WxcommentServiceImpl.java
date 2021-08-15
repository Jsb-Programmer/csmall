package com.cskaoyan.service.wx;

import com.cskaoyan.bean.BaseRespData2;
import com.cskaoyan.bean.WxListBaseParam2;
import com.cskaoyan.bean.pojo.Comment;
import com.cskaoyan.bean.pojo.CommentExample;
import com.cskaoyan.mapper.CommentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangbo
 * @description
 * @date 2021/8/15 17:44
 */
@Service
public class WxcommentServiceImpl implements WxCommentService{

    @Autowired
    CommentMapper commentMapper;

    @Override
    public BaseRespData2 queryList(Integer valueId, WxListBaseParam2 commentBaseParam, Integer type, Integer showType) {
        PageHelper.startPage(commentBaseParam.getPage(), commentBaseParam.getSize());

        CommentExample example = new CommentExample();

        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andValueIdEqualTo(valueId);
        criteria.andDeletedEqualTo(false);
        List<Comment> commentList = commentMapper.selectByExample(example);

        //可以获得分页信息,在PageInfo中放入查询结果
        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        long total = pageInfo.getTotal();//select count(*) 根据上面执行的查询拼接后面的

        return BaseRespData2.create(commentList, total,commentBaseParam.getPage());
    }
}

