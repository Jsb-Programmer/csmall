package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;

import com.cskaoyan.bean.pojo.Ad;
import com.cskaoyan.bean.pojo.AdExample;
import com.cskaoyan.mapper.AdMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-11 18:41
 **/
@Service
public class AdServiceImpl implements AdService{

    @Autowired
    AdMapper mapper;


    // 广告首页+多条件查询
    @Override
    public BaseRespData queryList(String name, String content, BaseParam baseParam) {
        // 分页
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());

        AdExample adExample = new AdExample();
        // 构造排序
        adExample.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());
        // 拼接查询条件
        AdExample.Criteria criteria = adExample.createCriteria();
        if (name != null && !"".equals(name)) {
            // name模糊查询
            criteria.andNameLike("%" + name + "%");
        }
        if (content != null && !"".equals(content)) {
            // content模糊查询
            criteria.andContentLike("%" + content + "%");
        }
        // delete的参数为false
        criteria.andDeletedEqualTo(false);
        // 查询
        List<Ad> item = mapper.selectByExample(adExample);

        // 将查询结果放到PageInfo中
        PageInfo<Ad> adPageInfo = new PageInfo<>(item);
        // 获得total
        long total = adPageInfo.getTotal();

        return BaseRespData.create(item, total);
    }

    // 新增广告
    @Override
    public Ad createAd(Ad ad) {
        // 补充ad的成员变量值
        ad.setAddTime(new Date());
        ad.setUpdateTime(new Date());
        ad.setDeleted(false);
        ad.setEnabled(true);

        // 添加到数据库
        int insertSelective = mapper.insertSelective(ad);

        return ad;
    }

    // 更新广告
    @Override
    public Ad updateAd(Ad ad) {
        // 更新到数据库中
        int update = mapper.updateByPrimaryKeySelective(ad);
        return ad;
    }

    // 删除广告
    @Override
    public int deleteAd(Ad ad) {
        // 根据id删除广告
        // 逻辑删除
        ad.setDeleted(true);
        int delete = mapper.updateByPrimaryKey(ad);
        return delete;
    }
}
