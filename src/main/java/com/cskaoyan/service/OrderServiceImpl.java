package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.*;
import com.cskaoyan.bean.vo.goodsVo.RespDetailData;
import com.cskaoyan.mapper.OrderGoodsMapper;
import com.cskaoyan.mapper.OrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author wpb
 * @Date 2021/8/11 21:34
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;

    @Override
    public BaseRespData query(Integer userId, String orderSn, Short[] orderStatusArray, BaseParam param) {
        PageHelper.startPage(param.getPage(),param.getLimit());

        //<where> <if text="userId!=null"> order by ${param.sort} ${param.order}
        OrderExample example = new OrderExample();
        //构造排序
        example.setOrderByClause(param.getSort()+" "+param.getOrder());
        //拼接条件
        OrderExample.Criteria criteria = example.createCriteria();

        if(userId!=null){
            criteria.andUserIdEqualTo(userId);
        }
        if(orderSn!=null&&!"".equals(orderSn)){
            criteria.andOrderSnEqualTo(orderSn);
        }

        if(orderStatusArray!=null&&orderStatusArray.length!=0){
            ArrayList<Short> shorts = new ArrayList<>();
            for (Short orderStatus : orderStatusArray) {
                shorts.add(orderStatus);
            }
            criteria.andOrderStatusIn(shorts);
        }
//        List<Order> items = orderMapper.selectByUserId(userId, orderSn, orderStatusArray);
        List<Order> items = orderMapper.selectByExample(example);
        PageInfo<Order> orderPageInfo = new PageInfo<>(items);
        long total = orderPageInfo.getTotal();
        return BaseRespData.create(items,total);
    }

    @Override
    public RespDetailData queryDetail(Integer id) {
        //GsonFormat
        Order order = orderMapper.selectByPrimaryKey(id);
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByOrderID(id);
        User user =orderGoodsMapper.selectJoinUser(id);
        RespDetailData.UserBean userBean = new RespDetailData.UserBean();
        userBean.setNickname(user.getNickname());
        userBean.setAvatar(user.getAvatar());
        RespDetailData respDetailData = new RespDetailData();
        respDetailData.setUser(userBean);
        respDetailData.setOrder(order);
        respDetailData.setOrderGoods(orderGoods);
        return respDetailData;
    }

    @Override
    public void deleteOrder(Integer id, BigDecimal goodsPrice) {
        //这里写假删除 ，然后controller层返回ok
    }

    @Override
    public BaseRespVo updateStatus(Order order) {
        //根据orderId 修改订单状态 然后插入 shipChannel shipSn 的信息
        orderMapper.updateByPrimaryKeySelective(order);
        return BaseRespVo.ok();
    }
//
//    @Override
//    public BaseRespVo updateStatus(Integer orderId, String shipChannel, String shipSn) {
//        //根据orderId 修改订单状态 然后插入 shipChannel shipSn 的信息
//       orderMapper.updateStatusById(orderId);
//       orderMapper.insertMessagebyId(orderId,shipChannel,shipSn);
//       return BaseRespVo.ok();
//
//    }
}
