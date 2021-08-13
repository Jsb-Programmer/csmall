package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.pojo.Goods;
import com.cskaoyan.bean.pojo.GrouponExample;
import com.cskaoyan.bean.pojo.GrouponRules;
import com.cskaoyan.mapper.GoodsMapper;
import com.cskaoyan.mapper.GrouponRulesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yangbo
 * @description
 * @date 2021/8/12 20:53
 */
@Service
public class GrouponServiceImpl implements GrouponService {

    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    @Autowired
    GoodsMapper goodsMapper;

    //查看全部团购规则信息
    @Override
    public BaseRespData queryList(BaseParam baseParam) {
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());

        GrouponExample example = new GrouponExample();

        //构造排序
        example.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());


        List<GrouponRules> grouponList = grouponRulesMapper.select(baseParam);

        //可以获得分页信息,在PageInfo中放入查询结果
        PageInfo<GrouponRules> pageInfo = new PageInfo<>(grouponList);
        long total = pageInfo.getTotal();//select count(*) 根据上面执行的查询拼接后面的

        return BaseRespData.create(grouponList, total);
    }

    //增加新的团购信息
    @Override
    public GrouponRules createGroupon(GrouponRules grouponRules) {
        grouponRules.setAddTime(new Date());
        grouponRules.setUpdateTime(new Date());
        Goods goods = goodsMapper.selectGoodsForGroupon(grouponRules.getGoodsId());
        grouponRules.setGoodsName(goods.getName());
        grouponRules.setPicUrl(goods.getPicUrl());
        grouponRules.setDeleted(false);
        int insert = grouponRulesMapper.insert(grouponRules);
        return grouponRules;
    }

    //更新团购信息
    @Override
    public int updateGroupon(GrouponRules grouponRules) {
        grouponRules.setUpdateTime(new Date());
        int code = grouponRulesMapper.updateByPrimaryKey(grouponRules);
        return code;
    }

    @Override
    public int deleteTopic(GrouponRules grouponRules) {
        grouponRules.setDeleted(true);
        int code = grouponRulesMapper.updateByPrimaryKey(grouponRules);
        return code;
    }
}
