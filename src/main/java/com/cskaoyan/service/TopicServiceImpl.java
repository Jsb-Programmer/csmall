package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.bo.promotion2BO.CreateTopicBO;
import com.cskaoyan.bean.pojo.Topic;
import com.cskaoyan.bean.pojo.TopicExample;
import com.cskaoyan.bean.vo.promotion2VO.CreateTopicVO;
import com.cskaoyan.mapper.TopicMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yangbo
 * @description
 * @date 2021/8/12 0:08
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicMapper topicMapper;

    @Override
    public BaseRespData queryList(BaseParam baseParam) {

        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());

        TopicExample example = new TopicExample();

        //构造排序
        example.setOrderByClause(baseParam.getSort()+" "+baseParam.getOrder());


        List<Topic> topicList = topicMapper.select(baseParam);

        //可以获得分页信息,在PageInfo中放入查询结果
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList);
        long total = pageInfo.getTotal();//select count(*) 根据上面执行的查询拼接后面的

        return BaseRespData.create(topicList,total);
    }

    @Override
    public CreateTopicVO createTopic(CreateTopicBO topicBO) {

        Topic topic = new Topic();
        topic.setSubtitle(topicBO.getSubtitle());
        topic.setContent(topicBO.getContent());
        topic.setPrice(topicBO.getPrice());
        topic.setGoods(topicBO.getGoods());
        topic.setReadCount(topicBO.getReadCount());
        topic.setAddTime(new Date());
        topic.setUpdateTime(new Date());
        topic.setTitle(topicBO.getTitle());
        //添加数据
        int code = topicMapper.insertSelective(topic);

        CreateTopicVO topicVO = new CreateTopicVO();
        if (code > 0){
            //查新专题自动生成数据
            topicVO = topicMapper.selectCreateTopic(topic);
            topicVO.setSubtitle(topicBO.getSubtitle());
            topicVO.setContent(topicBO.getContent());
            topicVO.setPrice(Integer.parseInt(topicBO.getPrice()));
            topicVO.setReadCount(topicBO.getReadCount());
            topicVO.setGoods(topicBO.getGoods());
            topicVO.setTitle(topicBO.getTitle());
        }

        return topicVO;
    }
}
