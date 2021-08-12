package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.bo.CreateCouponBo;
import com.cskaoyan.bean.pojo.Coupon;
import com.cskaoyan.bean.pojo.CouponUser;
import com.cskaoyan.bean.vo.coupon.ListUserDataVo;
import com.cskaoyan.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: mall
 * @description: 推广管理——优惠券管理
 * @author: Liu
 * @create: 2021-08-11 20:23
 **/

@RestController
@RequestMapping("admin/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    // 优惠券首页+多条件查询
    @RequestMapping("list")
    public BaseRespVo couponList(String name, Short type, Short status, BaseParam baseParam) {
        BaseRespData data = couponService.couponList(name, type, status, baseParam);
        return BaseRespVo.ok(data);
    }

    // 优惠券详情
    @RequestMapping("read")
    public BaseRespVo readCoupon(Integer id) {
        Coupon data = couponService.readCoupon(id);
        return BaseRespVo.ok(data);
    }

    // 显示领取优惠券的用户
    @RequestMapping("listuser")
    public BaseRespVo listuser(Integer couponId, Integer userId, Short status) {
        ListUserDataVo data = couponService.listuser(couponId, userId, status);
        return BaseRespVo.ok(data);
    }

    // 添加优惠券
    @RequestMapping("create")
    public BaseRespVo createCoupon(@RequestBody CreateCouponBo createCouponBo) {
        int affectRows = couponService.createCoupon(createCouponBo);
        if (affectRows == 1) {
            return BaseRespVo.ok(createCouponBo);
        }
        return BaseRespVo.fail("添加失败");
    }

    // 删除优惠券
    @RequestMapping("delete")
    public BaseRespVo deleteCoupon(@RequestBody Coupon coupon) {
        int affectRows = couponService.deleteCoupon(coupon);
        if (affectRows == 1) {
            return BaseRespVo.ok();
        }
        return BaseRespVo.fail("删除失败");
    }

    // 更新优惠券
    @RequestMapping("update")
    public BaseRespVo updateCoupon(@RequestBody Coupon coupon) {
        Coupon data = couponService.updateCoupon(coupon);

        return BaseRespVo.ok(data);
    }


}
