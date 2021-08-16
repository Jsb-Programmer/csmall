package com.cskaoyan.bean.vo.wxCoupon;

import com.cskaoyan.bean.vo.wxOrder.WxOrderBaseRespVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-15 17:00
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponBaseVo<T> {
    List<T> data;
    long count;

    public static <T> CouponBaseVo create(List<T> data, long count) {
        return new CouponBaseVo(data, count);
    }
}
