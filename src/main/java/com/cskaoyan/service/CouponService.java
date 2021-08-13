package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.pojo.Coupon;
import com.cskaoyan.bean.vo.coupon.ListUserDataVo;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-11 20:25
 **/
public interface CouponService {
    BaseRespData couponList(String name, Short type, Short status, BaseParam baseParam);

    Coupon readCoupon(Integer id);

    ListUserDataVo listuser(Integer couponId, Integer userId, Short status);

    int createCoupon(Coupon coupon);

    int deleteCoupon(Coupon coupon);

    Coupon  updateCoupon(Coupon coupon);
}
