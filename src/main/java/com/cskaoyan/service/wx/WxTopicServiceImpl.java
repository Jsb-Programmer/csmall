package com.cskaoyan.service.wx;

import com.cskaoyan.bean.bo.wxTopic.WxTopicBaseParam;
import com.cskaoyan.bean.pojo.Topic;
import com.cskaoyan.bean.pojo.TopicExample;
import com.cskaoyan.bean.vo.wxTopic.DetailTopicVO;
import com.cskaoyan.bean.vo.wxTopic.TopicBaseRespData;
import com.cskaoyan.mapper.TopicMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangbo
 * @description
 * @date 2021/8/15 14:36
 */
@Service
public class WxTopicServiceImpl implements WxTopicService {

    @Autowired
    TopicMapper topicMapper;

    //查看全部专题信息
    @Override
    public TopicBaseRespData queryList(WxTopicBaseParam wxTopicBaseParam) {
        PageHelper.startPage(wxTopicBaseParam.getPage(), wxTopicBaseParam.getSize());

        TopicExample example = new TopicExample();

        TopicExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<Topic> topicList = topicMapper.selectByExample(example);

        //可以获得分页信息,在PageInfo中放入查询结果
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList);
        long total = pageInfo.getTotal();//select count(*) 根据上面执行的查询拼接后面的

        return TopicBaseRespData.create(topicList, total,null);
    }

    //查看专题详情
    @Override
    public DetailTopicVO detail(Integer id) {

        DetailTopicVO detailTopicVO = new DetailTopicVO();
        detailTopicVO.setTopic(topicMapper.selectByPrimaryKey(id));
        detailTopicVO.setGoods(detailTopicVO.getTopic().getGoods());
        return detailTopicVO;
    }

    //查看相关专题内容
    @Override
    public List<Topic> related(Integer id) {
        Topic topic = topicMapper.selectByPrimaryKey(id);

        TopicExample example = new TopicExample();
        TopicExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andTitleEqualTo(topic.getTitle());
        criteria.andIdNotEqualTo(id);
        List<Topic> topicList = topicMapper.selectByExample(example);

        return topicList;
    }
}
