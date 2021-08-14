package com.cskaoyan.service.admin;

import com.cskaoyan.bean.vo.stat.StatGoodsVO;
import com.cskaoyan.bean.vo.stat.StatOrderVO;
import com.cskaoyan.bean.vo.stat.StatUserVO;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-12 18:44
 **/
public interface StatService {
    StatUserVO statUser();

    StatOrderVO statOrder();

    StatGoodsVO statGoods();

}
