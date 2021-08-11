package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.pojo.Order;
import com.cskaoyan.bean.pojo.OrderExample;
import com.cskaoyan.mapper.OrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author wpb
 * @Date 2021/8/11 21:34
 **/
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public BaseRespData query(Integer userId, Integer orderSn, ArrayList[] orderStatusArray, BaseParam param) {
        PageHelper.startPage(param.getPage(),param.getLimit());
        OrderExample example = new OrderExample();
        //构造排序
        example.setOrderByClause(param.getSort()+" "+param.getOrder());
        //拼接条件
        OrderExample.Criteria criteria = example.createCriteria();
       if (userId>0){
           criteria.addUserIdLike("%" + userId + "%");
       }
       if (orderSn>0){
           criteria.addOrderSnLike("%" + orderSn + "%");
       }
        List<Order> items = orderMapper.selectByExample(example);

       PageInfo<Order> orderPageInfo = new PageInfo<>(items);
        long total = orderPageInfo.getTotal();
        return BaseRespData.create(items,total);


    }
}
