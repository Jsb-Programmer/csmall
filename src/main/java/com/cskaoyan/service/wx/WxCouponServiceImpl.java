package com.cskaoyan.service.wx;

import com.cskaoyan.bean.bo.wxOrder.WxOrderBaseParamBO;
import com.cskaoyan.bean.pojo.*;
import com.cskaoyan.bean.vo.wxCoupon.CouponBaseVo;
import com.cskaoyan.mapper.CartMapper;
import com.cskaoyan.mapper.CouponMapper;
import com.cskaoyan.mapper.CouponUserMapper;
import com.cskaoyan.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-15 17:03
 **/

@Service
public class WxCouponServiceImpl implements WxCouponService {
    @Autowired
    CouponMapper couponMapper;

    @Autowired
    CouponUserMapper couponUserMapper;

    @Autowired
    CartMapper cartMapper;

    /**
     * 首页显示所有优惠券
     */
    @Override
    public CouponBaseVo getCouponList(WxOrderBaseParamBO baseParam) {
        // 分页
        PageHelper.startPage(baseParam.getPage(), baseParam.getSize());

        // 按条件查询
        CouponExample couponExample = new CouponExample();
        CouponExample.Criteria criteria = couponExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<Coupon> couponList = couponMapper.selectByExample(couponExample);

        // 放到分页中
        PageInfo<Coupon> couponPageInfo = new PageInfo<>(couponList);

        // 获得count
        long total = couponPageInfo.getTotal();

        return CouponBaseVo.create(couponList, total);
    }

    /**
     * 显示自己的优惠券
     */
    @Override
    public CouponBaseVo getMyCouponList(WxOrderBaseParamBO baseParam, Short status) {
        // 获取用户信息
        Subject subject = SecurityUtils.getSubject();
        Integer userId = (Integer) subject.getPrincipal();
//        Integer userId = 3;

        // 分页
        PageHelper.startPage(baseParam.getPage(), baseParam.getSize());

        // 从coupon-user表里查询对应的coupon
        CouponUserExample couponUserExample = new CouponUserExample();
        CouponUserExample.Criteria criteria1 = couponUserExample.createCriteria();
        criteria1.andUserIdEqualTo(userId);
        List<CouponUser> couponUserList = couponUserMapper.selectByExample(couponUserExample);


        List<Coupon> couponList = new ArrayList<>();
        // 从coupon表里查询
        for (CouponUser couponUser : couponUserList) {
            Integer couponId = couponUser.getCouponId();
            Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
            couponList.add(coupon);
        }

        // 放到分页中
        PageInfo<Coupon> couponPageInfo = new PageInfo<>(couponList);

        // 获得count
        long total = couponPageInfo.getTotal();

        return CouponBaseVo.create(couponList, total);
    }

    /**
     * 显示可选择的优惠券
     */
    @Override
    public List<Coupon> getSelectCouponList(Integer cartId, Integer grouponRulesId) {
        // 获取用户id
        Cart cart = cartMapper.selectByPrimaryKey(cartId);
        Integer userId = cart.getUserId();

        // 获取优惠券列表
        // 从coupon-user表里查询对应的coupon
        CouponUserExample couponUserExample = new CouponUserExample();
        CouponUserExample.Criteria criteria1 = couponUserExample.createCriteria();
        criteria1.andUserIdEqualTo(userId);
        List<CouponUser> couponUserList = couponUserMapper.selectByExample(couponUserExample);

        List<Coupon> couponList = new ArrayList<>();
        // 从coupon表里查询
        for (CouponUser couponUser : couponUserList) {
            Integer couponId = couponUser.getCouponId();
            Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
            couponList.add(coupon);
        }
        return couponList;
    }

    /**
     * 领取优惠券
     */
    @Override
    public void receiveCoupon(Integer couponId) {
        // 根据id查询优惠券
        Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
        // 获取用户信息
        Subject subject = SecurityUtils.getSubject();
        Integer userId = (Integer) subject.getPrincipal();

        // 创建coupon-user
        CouponUser couponUser = new CouponUser();
        couponUser.setUserId(userId);
        couponUser.setCouponId(couponId);
        couponUser.setStatus((short) 0);
        couponUser.setStartTime(coupon.getStartTime());
        couponUser.setEndTime(coupon.getEndTime());
        couponUser.setAddTime(new Date());
        couponUser.setUpdateTime(new Date());
        couponUser.setDeleted(false);
        // 插入到数据库
        couponUserMapper.insertSelective(couponUser);
        return;

    }

    /**
     * 兑换优惠券
     */
    @Override
    public int exchangeCoupon(Coupon coupon) {
        String code = coupon.getCode();
        // 判断code是否为空
        if (StringUtil.isEmpty(code)) {
            return 724;
        }
        // 查找对应的优惠券
        CouponExample couponExample = new CouponExample();
        CouponExample.Criteria criteria = couponExample.createCriteria();
        // 判断优惠券类型
        criteria.andTypeEqualTo((short) 2);
        // 判断是否删除
        criteria.andDeletedEqualTo(false);
        // code
        criteria.andCodeEqualTo(code);
        List<Coupon> coupons = couponMapper.selectByExample(couponExample);

        // 判断是否存在对应的优惠券
        if (coupons.isEmpty()) {
            return 724;
        }


        // 存在的话存到coupon-user表中
        // 获取userId
        Subject subject = SecurityUtils.getSubject();
        Integer userId = (Integer) subject.getPrincipal();

        for (Coupon coupon1 : coupons) {
            // 判断优惠券的状态
            if (coupon1.getStatus() == 0) {

                // 判断是否limit
                Short limit = coupon1.getLimit();
                if (limit > 0) {
                    // 判断目前该用户已经有几张该优惠券
                    CouponUserExample couponUserExample = new CouponUserExample();
                    CouponUserExample.Criteria criteria1 = couponUserExample.createCriteria();
                    criteria1.andUserIdEqualTo(userId);
                    criteria1.andCouponIdEqualTo(coupon1.getId());
                    List<CouponUser> couponUsers = couponUserMapper.selectByExample(couponUserExample);
                    int receiveCount = couponUsers.size();
                    if (receiveCount >= limit) {
                        return 750;
                    }
                }
                CouponUser couponUser = new CouponUser();
                couponUser.setUserId(userId);
                couponUser.setCouponId(coupon1.getId());
                couponUser.setStatus((short) 0);
                couponUser.setStartTime(coupon1.getStartTime());
                couponUser.setEndTime(coupon1.getEndTime());
                couponUser.setAddTime(new Date());
                couponUser.setUpdateTime(new Date());
                couponUser.setDeleted(false);



                // 存到数据库
                couponUserMapper.insertSelective(couponUser);
            } else return 723;
        }
        return 0;
    }


}