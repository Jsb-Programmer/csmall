package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.pojo.Topic;
import com.cskaoyan.bean.pojo.TopicExample;
import com.cskaoyan.bean.vo.topic.CreateTopicVO;
import com.cskaoyan.mapper.GoodsMapper;
import com.cskaoyan.mapper.TopicMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    //查看全部专题信息
    @Override
    public BaseRespData queryList(BaseParam baseParam) {

        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());

        TopicExample example = new TopicExample();

        //构造排序
        example.setOrderByClause(baseParam.getSort()+" "+baseParam.getOrder());

        TopicExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<Topic> topicList = topicMapper.selectByExample(example);

        //可以获得分页信息,在PageInfo中放入查询结果
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList);
        long total = pageInfo.getTotal();//select count(*) 根据上面执行的查询拼接后面的

        return BaseRespData.create(topicList,total);
    }

    //增加新的专题
    @Override
    public CreateTopicVO createTopic(Topic topic) {

        topic.setAddTime(new Date());
        topic.setUpdateTime(new Date());
        topic.setDeleted(false);
        //添加数据
        int code = topicMapper.insert(topic);

        CreateTopicVO topicVO = new CreateTopicVO();
            //查新专题自动生成数据
            topicVO.setId(topic.getId());
            topicVO.setAddTime(topic.getAddTime());
            topicVO.setUpdateTime(topic.getUpdateTime());
            topicVO.setSubtitle(topic.getSubtitle());
            topicVO.setContent(topic.getContent());
            topicVO.setPrice(topic.getPrice());
            topicVO.setReadCount(topic.getReadCount());
            topicVO.setGoods(topic.getGoods());
            topicVO.setTitle(topic.getTitle());

        return topicVO;
    }

    //更新专题
    @Override
    public Topic updateTopic(Topic topic) {
        topic.setUpdateTime(new Date());
        int code = topicMapper.updateByPrimaryKey(topic);
        topic = topicMapper.selectByPrimaryKey(topic.getId());
        return topic;
    }

    //逻辑删除专题
    @Override
    public int deleteTopic(Topic topic) {
        topic.setDeleted(true);
        int code = topicMapper.updateByPrimaryKey(topic);
        return code;
    }
}
