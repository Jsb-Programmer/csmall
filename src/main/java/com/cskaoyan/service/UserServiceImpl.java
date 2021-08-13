package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;
    /*
        @Override
        public BaseRespData query(String username, String mobile, BaseParam param) {
            PageHelper.startPage(param.getPage(), param.getLimit());
            List<CskaoyanmallUser> items = userMapper.select(username,mobile,param);
            //long total = items.size(); //22行这个查询如果不做分页的数据量 ≤limit

            //可以获得分页信息,在PageInfo中放入查询结果
            PageInfo<CskaoyanmallUser> pageInfo = new PageInfo<>(items);
            long total = pageInfo.getTotal(); //select count(*) 根据上面执行的查询拼接后面的

            return BaseRespData.create(items,total);
        }*/
    @Override
    public BaseRespData query(String username, String mobile, BaseParam param) {
        PageHelper.startPage(param.getPage(), param.getLimit());

        UserExample example = new UserExample();
        //构造排序
        example.setOrderByClause(param.getSort() + " " + param.getOrder());

        //拼接条件
        UserExample.Criteria criteria = example.createCriteria();
        if (username != null && !"".equals(username)) {
            criteria.andUsernameLike("%" + username + "%");//like需要自行拼接好%
        }
        if (mobile != null && !"".equals(mobile)) {
            criteria.andMobileEqualTo(mobile);
        }
        //通过example拼接条件 and username like #{} and mobile = #{}

        List<User> items = userMapper.selectByExample(example);
        //List<User> items = userMapper.select(username,mobile,param);
        //long total = items.size(); //22行这个查询如果不做分页的数据量 ≤limit

        //可以获得分页信息,在PageInfo中放入查询结果
        PageInfo<User> pageInfo = new PageInfo<>(items);
        long total = pageInfo.getTotal(); //select count(*) 根据上面执行的查询拼接后面的

        return BaseRespData.create(items,total);
    }
}
