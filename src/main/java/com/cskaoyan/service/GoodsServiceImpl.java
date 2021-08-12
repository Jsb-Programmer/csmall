package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.pojo.Goods;
import com.cskaoyan.bean.pojo.GoodsExample;
import com.cskaoyan.mapper.GoodsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;

    /**
     * 查询商品列表service层
     * @param goodsSn 商品编号
     * @param name 商品名称
     * @param baseParam
     * @return
     */
    @Transactional
    @Override
    public BaseRespData query(String goodsSn, String name, BaseParam baseParam) {
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        if (goodsSn != null && goodsSn.length() != 0)
            criteria.andGoodsSnEqualTo(goodsSn);
        if (name != null && name.length() != 0)
            criteria.andNameEqualTo(name);
        goodsExample.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());
        List<Goods> goodsList = goodsMapper.selectByExampleWithBLOBs(goodsExample);
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        long total = pageInfo.getTotal();
        return BaseRespData.create(goodsList, total);
    }
}
