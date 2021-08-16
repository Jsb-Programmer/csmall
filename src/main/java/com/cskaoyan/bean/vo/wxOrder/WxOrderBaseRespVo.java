package com.cskaoyan.bean.vo.wxOrder;

import com.cskaoyan.bean.BaseRespData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-14 14:40
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxOrderBaseRespVo<T> {
    List<T> data;
    long count;
    int totalPages;

    public static <T> WxOrderBaseRespVo create(List<T> items, long count, int totalPages) {
        return new WxOrderBaseRespVo(items, count, totalPages);
    }
}
