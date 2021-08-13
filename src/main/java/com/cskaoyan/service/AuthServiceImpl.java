package com.cskaoyan.service;

import com.cskaoyan.bean.pojo.GoodsExample;
import com.cskaoyan.bean.pojo.OrderExample;
import com.cskaoyan.bean.pojo.ProductExample;
import com.cskaoyan.bean.pojo.UserExample;
import com.cskaoyan.bean.vo.dashbord.AllKindsTotals;
import com.cskaoyan.mapper.GoodsMapper;
import com.cskaoyan.mapper.OrderMapper;
import com.cskaoyan.mapper.ProductMapper;
import com.cskaoyan.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-11 23:44
 **/

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    ProductMapper productMapper;


    @Override
    @Transactional
    public AllKindsTotals queryTotals() {
        long orderCount = orderMapper.countByExample(new OrderExample());
        long goodsCount = goodsMapper.countByExample(new GoodsExample());
        long userCount = userMapper.countByExample(new UserExample());
        long productCount = productMapper.countByExample(new ProductExample());
        return new AllKindsTotals(goodsCount,userCount,productCount,orderCount);
    }
}
