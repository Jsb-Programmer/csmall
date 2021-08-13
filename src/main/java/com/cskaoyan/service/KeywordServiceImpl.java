package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.pojo.Keyword;
import com.cskaoyan.bean.pojo.KeywordExample;
import com.cskaoyan.mapper.KeywordMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cskaoyan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-12 21:33
 **/
@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    KeywordMapper keywordMapper;

    /**
     * 显示关键词list
     */
    @Override
    public BaseRespData getKeywordList(BaseParam baseParam, String keyword, String url) {
        // 分页
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());

        KeywordExample keywordExample = new KeywordExample();
        // 构造排序
        keywordExample.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());

        // 拼接条件
        KeywordExample.Criteria criteria = keywordExample.createCriteria();
        if (keyword != null && !"".equals(keyword)) {
            criteria.andKeywordLike("%" + keyword + "%");
        }
        if (url != null && !"".equals(url)) {
            criteria.andUrlLike("%" + url + "%");
        }
        criteria.andDeletedEqualTo(false);
        // 查询
        List<Keyword> keywords = keywordMapper.selectByExample(keywordExample);

        // 获得分页信息，放入查询结果
        PageInfo<Keyword> keywordPageInfo = new PageInfo<>(keywords);
        long total = keywordPageInfo.getTotal();

        return BaseRespData.create(keywords, total);

    }

    /**
     * 创建关键词
     */
    @Override
    public Keyword createKeyword(Keyword keyword) {
        keyword.setAddTime (new Date ());
        keyword.setUpdateTime (new Date());
        int affectedRows = keywordMapper.insertSelective (keyword);
        return keyword;
    }

    /**
     * 修改关键词
     */
    @Override
    public Keyword updateKeyword(Keyword keyword) {
        keyword.setUpdateTime (new Date ());
        keywordMapper.updateByPrimaryKeySelective (keyword);

        return keyword;
    }

    /**
     * 删除关键词
     */
    @Override
    public int deleteKeyword(Keyword keyword) {
        keyword.setDeleted (true);
        keyword.setUpdateTime (new Date ());
        int i = keywordMapper.updateByPrimaryKeySelective(keyword);
        return i;
    }
}
