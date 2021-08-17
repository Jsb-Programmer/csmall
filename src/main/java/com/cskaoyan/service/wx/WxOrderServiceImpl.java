package com.cskaoyan.service.wx;

import com.cskaoyan.bean.bo.cart.CheckoutBO;
import com.cskaoyan.bean.bo.wxOrder.OrderCommentBo;
import com.cskaoyan.bean.bo.wxOrder.SubmitBo;
import com.cskaoyan.bean.bo.wxOrder.WxOrderBaseParamBO;
import com.cskaoyan.bean.pojo.*;
import com.cskaoyan.bean.pojo.System;
import com.cskaoyan.bean.vo.cart.CheckoutVO;
import com.cskaoyan.bean.vo.wxOrder.OrderDetailDataVo;
import com.cskaoyan.bean.vo.wxOrder.OrderListDataVo;
import com.cskaoyan.bean.vo.wxOrder.SubmitVo;
import com.cskaoyan.bean.vo.wxOrder.WxOrderBaseRespVo;
import com.cskaoyan.mapper.*;
import com.cskaoyan.utils.WxOrderUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-14 14:24
 **/

@Service
public class WxOrderServiceImpl implements WxOrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    WxOrderGoodsMapper orderGoodsMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    SystemMapper systemMapper;

    @Autowired
    CartService cartService;

    /**
     * 显示订单list
     */
    @Override
    public WxOrderBaseRespVo getOrderList(Integer showType, WxOrderBaseParamBO baseParamBO) {
        // 获取用户信息
        Subject subject = SecurityUtils.getSubject();
        Integer userId = (Integer) subject.getPrincipal();
//        Integer userId = 3;

        // 分页
        PageHelper.startPage(baseParamBO.getPage(), baseParamBO.getSize());

        OrderExample orderExample = new OrderExample();
        // 拼接查询条件
        OrderExample.Criteria criteria = orderExample.createCriteria();
        // 拼接用户信息
        criteria.andUserIdEqualTo(userId);
        // deleted
        criteria.andDeletedEqualTo(false);
        // 订单状态
        // 全部订单list
        Short[] statusList0 = {101, 102, 103, 201, 202, 203, 301, 401, 402};
        // 待付款
        Short[] statusList1 = {101};
        // 待发货
        Short[] statusList2 = {201};
        // 待收货
        Short[] statusList3 = {301};
        // 待评价
        Short[] statusList4 = {401, 402};
        if (showType == null || showType == 0) {
            criteria.andOrderStatusIn(Arrays.asList(statusList0));
        } else if (showType == 1) {
            criteria.andOrderStatusIn(Arrays.asList(statusList1));
        } else if (showType == 2) {
            criteria.andOrderStatusIn(Arrays.asList(statusList2));
        } else if (showType == 3) {
            criteria.andOrderStatusIn(Arrays.asList(statusList3));
        } else if (showType == 4) {
            criteria.andOrderStatusIn(Arrays.asList(statusList4));
        }
        // 根据条件查询
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        PageInfo<Order> orderPageInfo = new PageInfo<>(orderList);
        // 获得count和totalPages
        long count = orderPageInfo.getTotal();
        int totalPages = orderPageInfo.getPages();

        List<OrderListDataVo> listDataVoList = new ArrayList<>();
        for (Order order : orderList) {
            OrderListDataVo orderListDataVo = new OrderListDataVo();

            orderListDataVo.setId(order.getId());
            orderListDataVo.setOrderSn(order.getOrderSn());
            orderListDataVo.setActualPrice(order.getActualPrice());
            // 是否团购
            if (order.getGrouponPrice() == null) {
                orderListDataVo.setGroupin(false);
            } else {
                orderListDataVo.setGroupin(true);
            }
            // 获得OrderStatusText
            orderListDataVo.setOrderStatusText(WxOrderUtil.statusAlter(order.getOrderStatus()));
            // 获得HandleOption
            orderListDataVo.setHandleOption(WxOrderUtil.checkStatus(order.getOrderStatus()));
            // 获得goodsList
            WxOrderGoodsExample orderGoodsExample = new WxOrderGoodsExample();
            WxOrderGoodsExample.Criteria criteria1 = orderGoodsExample.createCriteria();
            criteria1.andOrderIdEqualTo(order.getId());
            List<WxOrderGoods> wxOrderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);
            orderListDataVo.setGoodsList(wxOrderGoods);

            listDataVoList.add(orderListDataVo);
        }

        // 如果没有订单，重定向
        if (listDataVoList.isEmpty()) {
            return null;
        }


        return WxOrderBaseRespVo.create(listDataVoList, count, totalPages);
    }

    /**
     * 显示订单detail
     */
    @Override
    public OrderDetailDataVo getOrderDetail(Integer orderId) {
        OrderDetailDataVo orderDetailDataVo = new OrderDetailDataVo();

        // orderInfo部分
        Order order = orderMapper.selectByPrimaryKey(orderId);
        OrderDetailDataVo.OrderInfoBean orderInfoBean = new OrderDetailDataVo.OrderInfoBean();
        // 给成员变量赋值
        BeanUtils.copyProperties(order, orderInfoBean, "orderStatusText", "handleOption");
        // orderStatusText
        orderInfoBean.setOrderStatusText(WxOrderUtil.statusAlter(order.getOrderStatus()));
        // handleOption
        orderInfoBean.setHandleOption(WxOrderUtil.checkStatus(order.getOrderStatus()));
        orderDetailDataVo.setOrderInfo(orderInfoBean);

        // orderGoods部分
        WxOrderGoodsExample wxOrderGoodsExample = new WxOrderGoodsExample();
        WxOrderGoodsExample.Criteria criteria = wxOrderGoodsExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<WxOrderGoods> wxOrderGoods = orderGoodsMapper.selectByExample(wxOrderGoodsExample);
        orderDetailDataVo.setOrderGoods(wxOrderGoods);

        return orderDetailDataVo;
    }

    /**
     * 取消订单
     */
    @Override
    public int cancelOrder(Order order) {
        // 更新订单
        order.setOrderStatus((short) 102);
        int i = orderMapper.updateByPrimaryKeySelective(order);
        return i;
    }

    /**
     * 退货订单
     */
    @Override
    public int refundOrder(Order order) {
        // 更新订单
        order.setOrderStatus((short) 202);
        int i = orderMapper.updateByPrimaryKeySelective(order);
        return i;
    }

    /**
     * 删除订单
     */
    @Override
    public int deleteOrder(Order order) {
        // 更新订单
        order.setDeleted(true);
        int i = orderMapper.updateByPrimaryKeySelective(order);
        return i;
    }

    /**
     * 显示商品评论界面
     */
    @Override
    public WxOrderGoods goods(Integer orderId, Integer goodsId) {
        WxOrderGoodsExample wxOrderGoodsExample = new WxOrderGoodsExample();
        WxOrderGoodsExample.Criteria criteria = wxOrderGoodsExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        criteria.andGoodsIdEqualTo(goodsId);
        List<WxOrderGoods> wxOrderGoods = orderGoodsMapper.selectByExample(wxOrderGoodsExample);
        return wxOrderGoods.get(0);
    }

    /**
     * 提交商品评论
     */
    @Override
    public void comment(OrderCommentBo orderCommentBo) {
        // 获得商品id
        WxOrderGoods wxOrderGoods = orderGoodsMapper.selectByPrimaryKey(orderCommentBo.getOrderGoodsId());
        Integer goodsId = wxOrderGoods.getGoodsId();
        // 增加到comment表
        Comment comment = new Comment();
        comment.setValueId(goodsId);
        comment.setType((byte) 3);
        comment.setContent(orderCommentBo.getContent());
        comment.setHasPicture(orderCommentBo.getHasPicture());
        comment.setPicUrls(orderCommentBo.getPicUrls());
        comment.setStar(orderCommentBo.getStar());
        comment.setAddTime(new Date());
        comment.setUpdateTime(new Date());
        comment.setDeleted(false);

        // 获取用户id
        Subject subject = SecurityUtils.getSubject();
        Integer userId = (Integer) subject.getPrincipals().getPrimaryPrincipal();
        comment.setUserId(userId);

        // 添加到comment表
        int i = commentMapper.insertSelective(comment);
        // 获取commentid
        Integer commentId = comment.getId();

        // 更新order——goods表
        wxOrderGoods.setComment(commentId);
        wxOrderGoods.setUpdateTime(new Date());
        orderGoodsMapper.updateByPrimaryKeySelective(wxOrderGoods);

        // 更新到order表中
        Integer orderId = wxOrderGoods.getOrderId();
        Order order = orderMapper.selectByPrimaryKey(orderId);
        order.setMessage(orderCommentBo.getContent());
        // 待评价商品数量
        Short commentsNum = order.getComments();
        if (commentsNum >= 1) {
            commentsNum = (short) (commentsNum - 1);
        }
        order.setComments(commentsNum);
        order.setUpdateTime(new Date());
        orderMapper.updateByPrimaryKey(order);

    }

    /**
     * 确认收货
     */
    @Override
    public void confirm(Order order) {
        // 更新订单
        order.setOrderStatus((short) 401);
        int i = orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 购买
     */
    @Override
    public SubmitVo submit(SubmitBo submitBo) {
        // 获得优惠钱数
        int couponId = submitBo.getCouponId();

        Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
        BigDecimal discount;
        if (coupon == null) {
             discount = BigDecimal.valueOf(0);
        } else {
             discount = coupon.getDiscount();
        }

        // 获取用户
        Subject subject = SecurityUtils.getSubject();
        Integer userId = (Integer) subject.getPrincipal();
        User user = userMapper.selectByPrimaryKey(userId);

        // 获得地址
        int addressId = submitBo.getAddressId();
        Address address = addressMapper.selectByPrimaryKey(addressId);

        // 获得配送费
        SystemExample systemExample = new SystemExample();
        SystemExample.Criteria criteria = systemExample.createCriteria();
        criteria.andKeyNameEqualTo("cskaoyan_mall_express_freight_value");
        List<System> systems = systemMapper.selectByExample(systemExample);
        System system = systems.get(0);
        String keyValue = system.getKeyValue();
        double freight1 = Double.valueOf(keyValue);
        BigDecimal freight = BigDecimal.valueOf(freight1);

        // 获得订单总价
        CheckoutBO checkoutBO = new CheckoutBO();
        checkoutBO.setCartId(submitBo.getCartId());
        checkoutBO.setCouponId(submitBo.getCouponId());

        CheckoutVO checkout = cartService.checkout(checkoutBO, userId);
        BigDecimal actualPrice = BigDecimal.valueOf(checkout.getActualPrice());


        // cart
        int cartId = submitBo.getCartId();
        Cart cart = cartMapper.selectByPrimaryKey(cartId);

        // 创建order对象
        Order order = new Order();
        order.setUserId(userId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = simpleDateFormat.format(new Date());
        order.setOrderSn(format);
        order.setOrderStatus((short) 201);
        order.setConsignee(user.getUsername());
        order.setMobile(user.getMobile());
        order.setAddress(address.getAddress());
        order.setMessage(submitBo.getMessage());
        order.setGoodsPrice(BigDecimal.valueOf(checkout.getGoodsTotalPrice()));
        order.setFreightPrice(freight);
        order.setCouponPrice(discount);
        order.setOrderPrice(actualPrice);
        order.setActualPrice(actualPrice);
        order.setAddTime(new Date());
        order.setUpdateTime(new Date());
        order.setIntegralPrice(BigDecimal.valueOf(0));
        order.setGrouponPrice(BigDecimal.valueOf(0));
        int i = orderMapper.insertSelective(order);
        Integer id = order.getId();
        SubmitVo submitVo = new SubmitVo();
        submitVo.setOrderId(id);

        // 创建一个orderGoodsList

        // 获得order-goods
        List<Cart> checkedGoodsList = checkout.getCheckedGoodsList();
        for (Cart cart1 : checkedGoodsList) {
            WxOrderGoods orderGoods = new WxOrderGoods();
            orderGoods.setOrderId(id);
            orderGoods.setGoodsId(cart1.getGoodsId());
            orderGoods.setGoodsName(cart1.getGoodsName());
            orderGoods.setGoodsSn(cart1.getGoodsSn());
            orderGoods.setProductId(cart1.getProductId());
            orderGoods.setNumber(cart1.getNumber());
            orderGoods.setPrice(cart1.getPrice());
            orderGoods.setSpecifications(cart1.getSpecifications());
            orderGoods.setPicUrl(cart1.getPicUrl());
            orderGoods.setComment(0);
            orderGoods.setAddTime(new Date());
            orderGoods.setUpdateTime(new Date());
            orderGoods.setDeleted(false);
            orderGoodsMapper.insertSelective(orderGoods);
        }


        return submitVo;
    }


}
