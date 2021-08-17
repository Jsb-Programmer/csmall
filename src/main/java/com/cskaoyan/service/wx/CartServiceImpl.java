package com.cskaoyan.service.wx;

import com.cskaoyan.bean.bo.cart.*;
import com.cskaoyan.bean.pojo.*;
import com.cskaoyan.bean.vo.cart.CartIndex;
import com.cskaoyan.bean.vo.cart.CartTotal;
import com.cskaoyan.bean.vo.cart.CheckoutVO;
import com.cskaoyan.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.System;
import java.math.BigDecimal;
import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    SystemMapper systemMapper;
    @Autowired
    CouponUserMapper couponUserMapper;
    @Autowired
    CouponMapper couponMapper;

    /**
     * 根据传入的id查询用户不同订单状态的数量  (wx/user/index)
     * @param userId
     * @return
     */
    @Override
    public com.cskaoyan.bean.vo.cart.Index userIndex(Integer userId) {
        //接收数据的map
        List<Index> list = orderMapper.selectOrderStatus(userId);
        HashMap<String, Integer> mapV = new HashMap<>();
        mapV.put("unrecv",0);
        mapV.put("uncomment",0);
        mapV.put("unpaid",0);
        mapV.put("unship",0);
        //
        for (Index index : list) {
            if (index.getOrder_status() == 101){
                Integer unpaid = mapV.get("unpaid");
                mapV.put("unpaid",unpaid+index.getCount());
            }else if (index.getOrder_status() == 201){
                Integer unship = mapV.get("unship");
                mapV.put("unship",unship+index.getCount());
            }else if (index.getOrder_status() == 301){
                Integer unrecv = mapV.get("unrecv");
                mapV.put("unrecv",unrecv+index.getCount());
            }else if (index.getOrder_status() == 401 || index.getOrder_status() == 402){
                Integer uncomment = mapV.get("uncomment");
                mapV.put("uncomment",uncomment+index.getCount());
            }
        }




//        //
//        //返回数据的map
//        mapV.put("unrecv",0);       //前缀为3
//        mapV.put("uncomment",0);    //前缀为4
//        mapV.put("unpaid",0);       //前缀为1
//        mapV.put("unship",0);       //前缀为2
//        //key值转换的map
//        HashMap<Integer, String> mapTemp = new HashMap<>();
//        mapTemp.put(3,"unrecv");
//        mapTemp.put(4,"uncomment");
//        mapTemp.put(1,"unpaid");
//        mapTemp.put(2,"unship");
//        //遍历mapB
//        for (Index index : list) {
//            //查询增加的数值
//            Integer count = index.getCount();
//            //查询对应的key
//            String key = mapTemp.get(index.getOrder_status() / 100);
//            //更新的数量
//            int update = mapV.get(key) + count;
//            mapV.put(key,update);
//
//        }
        com.cskaoyan.bean.vo.cart.Index order = new com.cskaoyan.bean.vo.cart.Index();
        order.setUncomment(mapV.get("uncomment"));
        order.setUnpaid(mapV.get("unpaid"));
        order.setUnrecv(mapV.get("unrecv"));
        order.setUnship(mapV.get("unship"));

        return order;
    }


    /**
     * 查询客户购物车中的商品,并做相关的计算
     * @param userId
     * @return
     */
    @Override
    public CartIndex index(Integer userId) {
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andUserIdEqualTo(userId);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        CartTotal cartTotal = new CartTotal();

        //遍历cart 填充cartTotal
        for (Cart cart : carts) {
            //获取单个加入购物车的订单的商品数量
            int number = cart.getNumber();
            //计算总价
            double price = cart.getPrice().doubleValue();
            cartTotal.addGoodsAmount(price*number);
            //将获取到的商品数量添加到cartTotal中
            cartTotal.addGoodsCount(number);
            //当check为true时添加
            if (cart.getChecked()==true){
                cartTotal.addCheckedGoodsCount(number);
                cartTotal.addCheckedGoodsAmount(price*number);
            }
        }

        CartIndex cartIndex = new CartIndex();
        cartIndex.setCartList(carts);
        cartIndex.setCartTotal(cartTotal);
        return cartIndex;
    }


    /**
     * 修改对应商品的 isCheck
     * @param checked
     * @param userId
     * @return
     */
    @Override
    public CartIndex checked(Checked checked, Integer userId) {
        //遍历list 将对应的商品checked=false
        List<Integer> productIds = checked.getProductIds();
        for (Integer productId : productIds) {
            CartExample cartExample = new CartExample();
            CartExample.Criteria criteria = cartExample.createCriteria();
            criteria.andProductIdEqualTo(productId);
            criteria.andUserIdEqualTo(userId);
            Cart cart = new Cart();
            cart.setUpdateTime(new Date(System.currentTimeMillis()));
            cart.setChecked(true);
            if (checked.getIsChecked()==0){
                cart.setChecked(false);
            }
            cartMapper.updateByExampleSelective(cart,cartExample);
        }

        //再次查询数据库并返回数据
        return index(userId);
    }

    /**
     * 添加购物车中的商品
     * 1.购物车中已存在
     *      a.没有被删除的商品,更新number
     *      b.被逻辑删除的商品,修改逻辑删除,并更新number
     * 2.购物车中不存在的商品,直接insert新纪录
     * @param addBO
     * @param userId
     * @return
     */
    @Override
    public int add(AddBO addBO, Integer userId) {
        //判断该用户购物车中是否存在同规格的商品,存在则加,不存在则创建
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andGoodsIdEqualTo(addBO.getGoodsId());
        criteria.andProductIdEqualTo(addBO.getProductId());
        criteria.andUserIdEqualTo(userId);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        //当carts不为0时,说明已存在
        int affectRows = 0;
        if (carts.size()!=0 && carts != null){
            short number = carts.get(0).getNumber();
            //更新number
            number+=addBO.getNumber();

            Cart cart = new Cart();
            cart.setNumber(number);
            cart.setDeleted(false);
            cart.setUpdateTime(new Date(System.currentTimeMillis()));
            affectRows = cartMapper.updateByExampleSelective(cart, cartExample);
        }else {
            //不存在则直接添加
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setGoodsId(addBO.getGoodsId());
            int number = addBO.getNumber();
            cart.setNumber((short) number);
            cart.setChecked(true);
            Date time = new Date(System.currentTimeMillis());
            cart.setAddTime(time);
            cart.setUpdateTime(time);
            cart.setDeleted(false);
            cart.setProductId(addBO.getProductId());
            //goods
            Goods goods = goodsMapper.selectByPrimaryKey(addBO.getGoodsId());

            cart.setGoodsSn(goods.getGoodsSn());
            cart.setGoodsName(goods.getName());
            cart.setPicUrl(goods.getPicUrl());

            //goods_product
            Product product = productMapper.selectByPrimaryKey(addBO.getProductId());
            cart.setPrice(product.getPrice());
            cart.setSpecifications(product.getSpecifications());
            affectRows = cartMapper.insert(cart);

        }
        //返回购物车中商品的数量
        return goodscount(userId);
    }


    /**
     * 逻辑删除购物车中的商品 并将number设为0
     * @param productIds
     * @param userId
     * @return
     */
    @Override
    public CartIndex delete(List<Integer> productIds, Integer userId) {
        //遍历list update detelted
        for (Integer productId : productIds) {
            CartExample cartExample = new CartExample();
            CartExample.Criteria criteria = cartExample.createCriteria();
            criteria.andUserIdEqualTo(userId);
            criteria.andProductIdEqualTo(productId);
            Cart cart = new Cart();
            cart.setUpdateTime(new Date(System.currentTimeMillis()));
            cart.setNumber((short) 0);
            cart.setDeleted(true);
            int i = cartMapper.updateByExampleSelective(cart, cartExample);
        }
        return index(userId);
    }

    /**
     * 修改购物车商品中的数量
     * @param updateBO
     * @return
     */
    @Override
    public int update(UpdateBO updateBO) {
        Cart cart = new Cart();
        cart.setUpdateTime(new Date(System.currentTimeMillis()));
        int number = updateBO.getNumber();
        cart.setNumber((short) number);
        cart.setId(updateBO.getId());
        int i = cartMapper.updateByPrimaryKeySelective(cart);
        return i;
    }


    /**
     * 立即购买快速加入购物车,
     * 1.商品已存在
     *      a.没有被删除,更新number
     *      b.已被逻辑删除,修改删除,并更新number
     * 2.商品不存在,直接insert新纪录
     * @param addBO
     * @param userId
     * @return
     */
    @Override
    public int fastadd(AddBO addBO, Integer userId) {
        // TODO: 2021/8/15 商品添加之后的数量 
        int add = add(addBO, userId);

        //更新数量
        int i = cartMapper.updateNumber(addBO.getNumber(),userId,addBO,new Date());
        return add;
    }


    /**
     * 购物车内商品总数量
     * @param userId
     * @return
     */
    @Override
    public int goodscount(Integer userId) {
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andDeletedEqualTo(false);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        int total = 0;
        for (Cart cart : carts) {
            total += cart.getNumber();
        }
        return total;
    }

    @Override
    public CheckoutVO checkout(CheckoutBO checkoutBO, Integer userId) {
        CheckoutVO checkoutVO = new CheckoutVO();
        //查询客户cart 中check的商品
        List<Cart> carts ;
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        //判断下单的位置
        if (checkoutBO.getCartId() != 0) {
            Cart cart = cartMapper.selectCartNewest(userId);
            carts = new ArrayList<>();
            carts.add(cart);
        }else {
            criteria.andDeletedEqualTo(false);
            criteria.andUserIdEqualTo(userId);
            criteria.andCheckedEqualTo(true);
            carts = cartMapper.selectByExample(cartExample);
        }
        checkoutVO.setCheckedGoodsList(carts);

        //查询客户的收获地址
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria exampleCriteria = addressExample.createCriteria();
//        exampleCriteria.andIsDefaultEqualTo(true);
        exampleCriteria.andUserIdEqualTo(userId);
        exampleCriteria.andIdEqualTo(checkoutBO.getAddressId());
        Address address = addressMapper.selectByExample(addressExample).get(0);
        checkoutVO.setCheckedAddress(address);
        checkoutVO.setAddressId(address.getId());

        //计算
//        //团购判断 通过集合计算购物车中参与团购的商品   todo
//        //参与团购商品的id
//        List<Integer> listGroupTotal = grouponMapper.selectGrouponGoodsId();
//        //cart中团购的商品
//        List<Integer> listGroupCart = cartMapper.selectGrouponGoodsId(userId);
//        HashSet<Integer> setCart = new HashSet<Integer>(listGroupCart);
//        //通过交集计算出购物车中参与团购的商品id
//        listGroupCart.retainAll(listGroupTotal);
//        //计算出购物车中不参加团购的商品id
//        setCart.removeAll(listGroupTotal);
//
//        //团购减免金额的计算
//
        checkoutVO.setGrouponPrice(0);
        checkoutVO.setGrouponRulesId(0);

        //优惠券判断,通过查询该用户所有的优惠券,来选择一张优惠力度最大的优惠券
        //使用过的优惠券要做处理
        //通过传的优惠券id判断是否有没有优惠券
        Integer couponId = checkoutBO.getCouponId();
        checkoutVO.setCouponId(couponId);
        if (couponId ==0){
            //没有优惠券可用
            checkoutVO.setCouponPrice(0.0);
        }else {
            //查询该优惠券,并使用
            Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
            if (coupon == null) {
                checkoutVO.setCouponPrice(0.0);
            }else {
                checkoutVO.setCouponPrice(coupon.getDiscount().doubleValue());
//                CouponUserExample couponUserExample = new CouponUserExample();
//                CouponUserExample.Criteria couponUserExampleCriteria = couponUserExample.createCriteria();
//                couponUserExampleCriteria.andUserIdEqualTo(userId);
//                couponUserExampleCriteria.andCouponIdEqualTo(couponId);
//                CouponUser couponUser = new CouponUser();
//                couponUser.setUpdateTime(new Date(System.currentTimeMillis()));
//                couponUser.setStatus((short) 1);
//                int update = couponUserMapper.updateByExampleSelective(couponUser, couponUserExample);
            }
        }
        //可用的优惠券数量
        // TODO: 2021/8/16
        CouponUserExample example = new CouponUserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<CouponUser> couponUsers = couponUserMapper.selectByExample(example);
        checkoutVO.setAvailableCouponLength(couponUsers.size());
        //商品总金额
        double totalPrice = 0;
        for (Cart cart : carts) {
            //同种多个商品的价格总和
            double price = cart.getPrice().doubleValue() * cart.getNumber().doubleValue();
            totalPrice += price;
        }
        //运费计算 判断订单总金额是否达到 包邮标准
        SystemExample exampleMax = new SystemExample();
        exampleMax.createCriteria().andKeyNameEqualTo("cskaoyan_mall_express_freight_min");
        List<com.cskaoyan.bean.pojo.System> systemsMax = systemMapper.selectByExample(exampleMax);

        SystemExample exampleFreight = new SystemExample();
        exampleFreight.createCriteria().andKeyNameEqualTo("cskaoyan_mall_express_freight_value");
        List<com.cskaoyan.bean.pojo.System> systemsFreight = systemMapper.selectByExample(exampleFreight);

        double keyValue = Double.parseDouble(systemsMax.get(0).getKeyValue());
        if (totalPrice<keyValue){
            checkoutVO.setFreightPrice(Integer.parseInt(systemsFreight.get(0).getKeyValue()));
        }else {
            checkoutVO.setFreightPrice(0);
        }

        checkoutVO.setGoodsTotalPrice(totalPrice);
        checkoutVO.setActualPrice(totalPrice-checkoutVO.getGrouponPrice()-checkoutVO.getCouponPrice()+checkoutVO.getFreightPrice());
        checkoutVO.setOrderTotalPrice(totalPrice-checkoutVO.getGrouponPrice()+checkoutVO.getFreightPrice());


        return checkoutVO;
    }
}
