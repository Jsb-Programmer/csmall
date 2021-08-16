package com.cskaoyan.service.wx;

import com.cskaoyan.bean.bo.wxOrder.ReceiveCouponBo;
import com.cskaoyan.bean.bo.wxOrder.WxOrderBaseParamBO;
import com.cskaoyan.bean.pojo.Coupon;
import com.cskaoyan.bean.vo.wxCoupon.CouponBaseVo;

import java.util.List;

/**
 * @program: mall
 * @description: 前台优惠券
 * @author: Liu
 * @create: 2021-08-15 17:03
 **/
public interface WxCouponService {
    CouponBaseVo getCouponList(WxOrderBaseParamBO baseParam);

    CouponBaseVo getMyCouponList(WxOrderBaseParamBO baseParam, Short status);

    List<Coupon> getSelectCouponList(Integer cartId, Integer grouponRulesId);

    int receiveCoupon(ReceiveCouponBo couponBo);

    int exchangeCoupon(Coupon coupon);
}
