package com.cskaoyan.service.wx;

import com.cskaoyan.bean.bo.wxfootprint.FootprintListBO;
import com.cskaoyan.bean.bo.wxfootprint.FootprintPageBO;
import com.cskaoyan.bean.pojo.FootPrint;
import com.cskaoyan.bean.pojo.FootPrintExample;
import com.cskaoyan.bean.pojo.Goods;
import com.cskaoyan.mapper.FootPrintMapper;
import com.cskaoyan.mapper.GoodsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-16 14:34
 **/

@Service
public class WxFootprintServiceImpl implements WxFootprintService{

    @Autowired
    FootPrintMapper footPrintMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    @Transactional
    public FootprintPageBO footprintList(Integer page,Integer size) {
        Subject subject = SecurityUtils.getSubject();
        Integer principal = (Integer) subject.getPrincipal();
        PageHelper.startPage(page,size);
        FootPrintExample footPrintExample = new FootPrintExample();
        footPrintExample.setOrderByClause("add_time desc");
        FootPrintExample.Criteria criteria = footPrintExample.createCriteria();
        criteria.andUserIdEqualTo(principal);
        criteria.andDeletedEqualTo(false);

        List<FootPrint> footPrints = footPrintMapper.selectByExample(footPrintExample);

        ArrayList<FootprintListBO> footprintListBOS = new ArrayList<>();
        for (FootPrint footPrint : footPrints) {
            FootprintListBO footprintListBO = new FootprintListBO();
            footprintListBO.setAddTime(footPrint.getAddTime());
            footprintListBO.setGoodsId(footPrint.getGoodsId());
            footprintListBO.setId(footPrint.getId());
            Goods goods = goodsMapper.selectByPrimaryKey(footPrint.getGoodsId());
            footprintListBO.setBrief(goods.getBrief());
            footprintListBO.setPicUrl(goods.getPicUrl());
            footprintListBO.setName(goods.getName());
            footprintListBO.setRetailPrice(goods.getRetailPrice());
            footprintListBOS.add(footprintListBO);
        }

        long total = new PageInfo<FootPrint>(footPrints).getTotal();

        return new FootprintPageBO(footprintListBOS,total);
    }

    /**
     * 删除历史足迹 by id
     * @param id
     */
    @Override
    public void deleteFootprintById(Integer id) {
        FootPrint footPrint = footPrintMapper.selectByPrimaryKey(id);
        footPrint.setDeleted(true);
        footPrintMapper.updateByPrimaryKeySelective(footPrint);
    }
}
