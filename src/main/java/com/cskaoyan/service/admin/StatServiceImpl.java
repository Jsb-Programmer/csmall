package com.cskaoyan.service.admin;

import com.cskaoyan.bean.vo.stat.*;
import com.cskaoyan.mapper.StatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-12 18:45
 **/

@Service
public class StatServiceImpl implements StatService{

    @Autowired
    StatMapper statMapper;

    /**
     * 用户统计
     */
    @Override
    public StatUserVO statUser() {
        StatUserVO statUserVO = new StatUserVO();
        List<String> columns = new ArrayList<>();
        columns.add("day");
        columns.add("users");
        statUserVO.setColumns(columns);

        List<RowsUserBean> rowsUserBeans = statMapper.selectGroupByAddtime();
        statUserVO.setRows(rowsUserBeans);

        return statUserVO;
    }

    /**
     * 订单统计
     */
    @Override
    public StatOrderVO statOrder() {
        StatOrderVO statOrderVO = new StatOrderVO();
        List<String> columns = new ArrayList<>();
        columns.add("day");
        columns.add("orders");
        columns.add("customers");
        columns.add("amount");
        columns.add("pcr");
        statOrderVO.setColumns(columns);

        List<RowsOrderBean> rowsOrderBeans = statMapper.selectOrderGroupByAddtime();
        statOrderVO.setRows(rowsOrderBeans);

        return statOrderVO;
    }

    /**
     * 商品统计
     */
    @Override
    public StatGoodsVO statGoods() {
        StatGoodsVO statGoodsVO = new StatGoodsVO();
        List<String> columns = new ArrayList<>();
        columns.add("day");
        columns.add("orders");
        columns.add("products");
        columns.add("amount");
        statGoodsVO.setColumns(columns);

        List<RowsGoodsBean> rowsGoodsBeans = statMapper.selectGoodsGroupByAddtime();
        statGoodsVO.setRows(rowsGoodsBeans);

        return statGoodsVO;
    }
}
