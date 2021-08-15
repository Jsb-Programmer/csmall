package com.cskaoyan.service.wx;

import com.cskaoyan.bean.BaseRespData2;
import com.cskaoyan.bean.WxListBaseParam2;
import com.cskaoyan.bean.pojo.Topic;
import com.cskaoyan.bean.pojo.TopicExample;
import com.cskaoyan.bean.vo.wxTopic.DetailTopicVO;
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
    public BaseRespData2 queryList(WxListBaseParam2 topicBaseParam) {
        PageHelper.startPage(topicBaseParam.getPage(), topicBaseParam.getSize());

        TopicExample example = new TopicExample();

        TopicExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<Topic> topicList = topicMapper.selectByExample(example);

        //可以获得分页信息,在PageInfo中放入查询结果
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList);
        long total = pageInfo.getTotal();//select count(*) 根据上面执行的查询拼接后面的

        return BaseRespData2.create(topicList, total,null);
    }

    //查看专题详情
    @Override
    public DetailTopicVO detail(Integer id) {

        DetailTopicVO detailTopicVO = new DetailTopicVO();
        detailTopicVO.setTopic(topicMapper.selectByPrimaryKey(id));
        detailTopicVO.setGoods(detailTopicVO.getTopic().getGoods());
        return detailTopicVO;
    }
}
