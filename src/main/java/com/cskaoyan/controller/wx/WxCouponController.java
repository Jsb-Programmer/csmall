package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.bo.wxOrder.ReceiveCouponBo;
import com.cskaoyan.bean.bo.wxOrder.WxOrderBaseParamBO;
import com.cskaoyan.bean.pojo.Coupon;
import com.cskaoyan.bean.vo.wxCoupon.CouponBaseVo;
import com.cskaoyan.service.wx.WxCouponService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: mall
 * @description: 前台优惠券
 * @author: Liu
 * @create: 2021-08-15 17:02
 **/

@RestController
@RequestMapping("wx/coupon")
public class WxCouponController {

    @Autowired
    WxCouponService couponService;

    /**
     * 首页显示所有优惠券
     */
    @RequestMapping("list")
    public BaseRespVo couponList(WxOrderBaseParamBO baseParam) {
        CouponBaseVo data = couponService.getCouponList(baseParam);
        return BaseRespVo.ok(data);
    }

    /**
     * 显示自己的优惠券
     */
    @RequestMapping("mylist")
    public BaseRespVo myCouponList(WxOrderBaseParamBO baseParam, Short status) {
        CouponBaseVo data = couponService.getMyCouponList(baseParam, status);
        return BaseRespVo.ok(data);
    }

    /**
     * 下单时选择可使用的优惠券
     */
    @RequestMapping("selectlist")
    public BaseRespVo selectCouponList(Integer cartId, Integer grouponRulesId) {
        List<Coupon> data = couponService.getSelectCouponList(cartId, grouponRulesId);
        return BaseRespVo.ok(data);
    }

    /**
     * 领取优惠券
     */
    @RequestMapping("receive")
    public BaseRespVo receiveCoupon(@RequestBody ReceiveCouponBo couponBo) {
        int code = couponService.receiveCoupon(couponBo);
        if (code == 0) {
            return BaseRespVo.ok();
        } else if (code == 750) {
            return BaseRespVo.fail("领取数量超限", 742);
        } else {
            return BaseRespVo.fail("优惠券已下架", 404);

        }
    }

    /**
     * 领取优惠券
     */
    @RequestMapping("exchange")
    public BaseRespVo exchangeCoupon(@RequestBody Coupon coupon) {
        int respCode = couponService.exchangeCoupon(coupon);
        if (respCode == 0) {
            return BaseRespVo.ok();
        } else if (respCode == 724) {
            return BaseRespVo.fail("券码不正确", 742);
        } else if (respCode == 750) {
            return BaseRespVo.fail("领取数量超限", 742);
        } else {
            return BaseRespVo.fail("优惠券已下架", 404);

        }


    }
}