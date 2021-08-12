package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.bo.CreateCouponBo;
import com.cskaoyan.bean.pojo.*;
import com.cskaoyan.bean.vo.coupon.ListUserDataVo;
import com.cskaoyan.mapper.CouponMapper;
import com.cskaoyan.mapper.CouponUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-11 20:28
 **/

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponMapper mapper;

    @Autowired
    CouponUserMapper couponUserMapper;

    // 优惠券首页+多条件查询
    @Override
    public BaseRespData couponList(String name, Short type, Short status, BaseParam baseParam) {
        // 分页
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());

        CouponExample couponExample = new CouponExample();
        // 构造排序
        couponExample.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());

        // 拼接查询条件
        CouponExample.Criteria criteria = couponExample.createCriteria();
        if (type != null && !"".equals(type)) {
            // type查询
            criteria.andTypeEqualTo(type);
        }
        if (status != null && !"".equals(status)) {
            // status查询
            criteria.andStatusEqualTo(status);
        }
        if (name != null && !"".equals(name)) {
            // name模糊查询
            criteria.andNameLike("%" + name + "%");
        }
        // delete的参数为false
        criteria.andDeletedEqualTo(false);

        // 查询
        List<Coupon> item = mapper.selectByExample(couponExample);

        // 将查询结果放到PageInfo中
        PageInfo<Coupon> couponPageInfo = new PageInfo<>(item);
        // 获得total
        long total = couponPageInfo.getTotal();

        return BaseRespData.create(item, total);
    }

    // 优惠券详情
    @Override
    public Coupon readCoupon(Integer id) {
        // 根据id查找
        Coupon coupon = mapper.selectByPrimaryKey(id);
        return coupon;
    }

    // 显示领取优惠券的用户
    @Override
    public ListUserDataVo listuser(Integer couponId, Integer userId, Short status) {
        // 根据couponId查找Coupon
        Coupon coupon = mapper.selectByPrimaryKey(couponId);
        // 赋值给vo
        ListUserDataVo listUserDataVo = new ListUserDataVo();
        listUserDataVo.setId(coupon.getId());
        listUserDataVo.setName(coupon.getName());
        listUserDataVo.setTag(coupon.getTag());
        listUserDataVo.setTotal(coupon.getTotal());
        listUserDataVo.setDiscount(coupon.getDiscount());
        listUserDataVo.setMin(coupon.getMin());
        listUserDataVo.setLimit(coupon.getLimit());
        listUserDataVo.setType(coupon.getType());
        listUserDataVo.setStatus(coupon.getStatus());
        listUserDataVo.setGoodsType(coupon.getGoodsType());
        listUserDataVo.setTimeType(coupon.getTimeType());
        listUserDataVo.setDays(coupon.getDays());
        listUserDataVo.setStartTime(coupon.getStartTime());
        listUserDataVo.setEndTime(coupon.getEndTime());
        listUserDataVo.setAddTime(coupon.getAddTime());
        listUserDataVo.setUpdateTime(coupon.getUpdateTime());
        listUserDataVo.setDeleted(coupon.getDeleted());

        // goodsValue赋值
        // 该功能未开通，所以为null
        listUserDataVo.setGoodsValue(null);

        // 查询coupon-user
        CouponUserExample couponUserExample = new CouponUserExample();
        CouponUserExample.Criteria criteria = couponUserExample.createCriteria();
        // 根据couponId查找
        criteria.andCouponIdEqualTo(couponId);
        if (userId != null && !"".equals(userId)) {
            // userId查询
            criteria.andUserIdEqualTo(userId);
        }
        if (status != null && !"".equals(status)) {
            // status查询
            criteria.andStatusEqualTo(status);
        }
        // delete的参数为false
        criteria.andDeletedEqualTo(false);
        // 查询
        List<CouponUser> item = couponUserMapper.selectByExample(couponUserExample);
        // 将查询结果放到PageInfo中
        PageInfo<CouponUser> couponUserPageInfo = new PageInfo<>(item);
        // 获得total
        long total = couponUserPageInfo.getTotal();

        BaseRespData<CouponUser> tBaseRespData = new BaseRespData();
        tBaseRespData.setTotal(total);
        tBaseRespData.setItems(item);

        // desc赋值
        listUserDataVo.setDesc(BaseRespVo.ok(tBaseRespData));

        return listUserDataVo;
    }

    // 添加优惠券
    @Override
    public int createCoupon(CreateCouponBo createCouponBo) {
        createCouponBo.setGoodsValue(null);
        Coupon coupon = new Coupon();
        coupon.setName(createCouponBo.getName());
        coupon.setDesc(createCouponBo.getName());
        coupon.setTag(createCouponBo.getName());
        coupon.setTotal(createCouponBo.getTotal());
        coupon.setDiscount(createCouponBo.getDiscount());
        coupon.setMin(createCouponBo.getMin());
        coupon.setLimit(createCouponBo.getLimit());
        coupon.setType(createCouponBo.getType());
        coupon.setStatus(createCouponBo.getStatus());
        coupon.setGoodsType(createCouponBo.getGoodsType());
        coupon.setGoodsValue(null);
        coupon.setTimeType(createCouponBo.getTimeType());
        coupon.setDays(createCouponBo.getDays());
        coupon.setStartTime(createCouponBo.getStartTime());
        coupon.setEndTime(createCouponBo.getEndTime());
        coupon.setAddTime(new Date());
        coupon.setUpdateTime(new Date());
        coupon.setDeleted(false);
        int insertSelective = mapper.insertSelective(coupon);
        return insertSelective;
    }

    // 删除优惠券
    @Override
    public int deleteCoupon(Coupon coupon) {
//        Coupon coupon = new Coupon();
//        coupon.setName(createCouponBo.getName());
//        coupon.setDesc(createCouponBo.getName());
//        coupon.setTag(createCouponBo.getName());
//        coupon.setTotal(createCouponBo.getTotal());
//        coupon.setDiscount(createCouponBo.getDiscount());
//        coupon.setMin(createCouponBo.getMin());
//        coupon.setLimit(createCouponBo.getLimit());
//        coupon.setType(createCouponBo.getType());
//        coupon.setStatus(createCouponBo.getStatus());
//        coupon.setGoodsType(createCouponBo.getGoodsType());
//        coupon.setGoodsValue(null);
//        coupon.setTimeType(createCouponBo.getTimeType());
//        coupon.setDays(createCouponBo.getDays());
//        coupon.setStartTime(createCouponBo.getStartTime());
//        coupon.setEndTime(createCouponBo.getEndTime());
//        coupon.setAddTime(createCouponBo.getAddTime());
//        coupon.setUpdateTime(createCouponBo.getUpdateTime());
        coupon.setDeleted(true);

        int i = mapper.updateByPrimaryKeySelective(coupon);
        return i;
    }

    // 更新优惠券
    @Override
    public Coupon  updateCoupon(Coupon coupon) {
        // 更新到数据库中
        int i = mapper.updateByPrimaryKey(coupon);
        Coupon couponVo = mapper.selectByPrimaryKey(coupon.getId());
        return couponVo;
    }
}
