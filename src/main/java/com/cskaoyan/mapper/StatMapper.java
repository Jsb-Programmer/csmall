package com.cskaoyan.mapper;

import com.cskaoyan.bean.vo.stat.RowsGoodsBean;
import com.cskaoyan.bean.vo.stat.RowsOrderBean;
import com.cskaoyan.bean.vo.stat.RowsUserBean;

import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-12 20:33
 **/
public interface StatMapper {
    List<RowsUserBean> selectGroupByAddtime();

    List<RowsOrderBean> selectOrderGroupByAddtime();

    List<RowsGoodsBean> selectGoodsGroupByAddtime();
}
