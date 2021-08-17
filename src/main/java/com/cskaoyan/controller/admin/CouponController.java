package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.Coupon;
import com.cskaoyan.bean.vo.coupon.ListUserDataVo;
import com.cskaoyan.service.admin.CouponService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 优惠券首页+多条件查询
     * @param name 优惠券名字
     * @param type 优惠券类型
     * @param status 优惠券状态
     * @param baseParam 排序+分页
     * @return BaseRespVo
     */
    @RequiresPermissions("admin:coupon:list")
    @RequestMapping("list")
    public BaseRespVo couponList(String name, Short type, Short status, BaseParam baseParam) {
        BaseRespData data = couponService.couponList(name, type, status, baseParam);
        return BaseRespVo.ok(data);
    }

    /**
     * 优惠券详情
     * @param id 优惠券id
     * @return BaseRespVo
     */
    @RequiresPermissions("admin:coupon:read")
    @RequestMapping("read")
    public BaseRespVo readCoupon(Integer id) {
        Coupon data = couponService.readCoupon(id);
        return BaseRespVo.ok(data);
    }

    /**
     * 显示领取优惠券的用户
     * @param couponId couponId
     * @param userId userId
     * @param status status
     * @return BaseRespVo
     */
    @RequiresPermissions("admin:coupon:listuser")
    @RequestMapping("listuser")
    public BaseRespVo listuser(Integer couponId, Integer userId, Short status) {
        ListUserDataVo data = couponService.listuser(couponId, userId, status);
        return BaseRespVo.ok(data);
    }

    /**
     * 添加优惠券
     * @param coupon createCouponBo
     * @return affectRows
     */
    @RequiresPermissions("admin:coupon:create")
    @RequestMapping("create")
    public BaseRespVo createCoupon(@RequestBody Coupon coupon) {
        if (coupon.getGoodsType() == 1 || coupon.getGoodsType() == 2) {
            return BaseRespVo.fail("暂时只支持全商品");
        }
        int affectRows = couponService.createCoupon(coupon);
        if (affectRows == 1) {
            return BaseRespVo.ok(coupon);
        }
        return BaseRespVo.fail("添加失败");
    }

    /**
     * 删除优惠券
     * @param coupon Coupon
     * @return BaseRespVo
     */
    @RequiresPermissions("admin:coupon:delete")
    @RequestMapping("delete")
    public BaseRespVo deleteCoupon(@RequestBody Coupon coupon) {
        int affectRows = couponService.deleteCoupon(coupon);
        if (affectRows == 1) {
            return BaseRespVo.ok();
        }
        return BaseRespVo.fail("删除失败");
    }

    /**
     * 更新优惠券
     * @param coupon Coupon
     * @return BaseRespVo
     */
    @RequiresPermissions("admin:coupon:update")
    @RequestMapping("update")
    public BaseRespVo updateCoupon(@RequestBody Coupon coupon) {
        Coupon data = couponService.updateCoupon(coupon);

        return BaseRespVo.ok(data);
    }


}
