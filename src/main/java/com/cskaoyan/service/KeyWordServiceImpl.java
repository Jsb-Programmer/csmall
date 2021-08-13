package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.KeyWord;
import com.cskaoyan.bean.pojo.KeyWordExample;
import com.cskaoyan.mapper.KeyWordMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName KeyWordServiceImpl
 * @Description TODO
 * @Author Programmer
 * @Date 2021/8/13 15:05
 **/
@Service
public class KeyWordServiceImpl implements KeyWordService {
    @Autowired
    KeyWordMapper keyWordMapper;
    @Override
    public BaseRespData querry(BaseParam param, String keyword, String url) {
        PageHelper.startPage(param.getPage(),param.getLimit());
        KeyWordExample example = new KeyWordExample();
        //构造排序
        example.setOrderByClause(param.getSort()+" "+param.getOrder());
        //拼接条件
        KeyWordExample.Criteria criteria = example.createCriteria();
        if (keyword!=null&&!"".equals(keyword)){
            criteria.andKeywordLike("%"+keyword+"%");
        }
        if (url!=null&&!"".equals(url)){
            criteria.andUrlLike("%"+url+"%");
        }
        List<KeyWord> items = keyWordMapper.selectByExample(example);
        PageInfo<KeyWord> keyWordPageInfo = new PageInfo<>(items);
        long total = keyWordPageInfo.getTotal();
        return BaseRespData.create(items,total);

    }

    @Override
    public BaseRespVo create(KeyWord keyWord) {
        keyWordMapper.insertSelective(keyWord);
        return BaseRespVo.ok();
    }

    @Override
    public BaseRespVo update(KeyWord keyWord) {
        keyWordMapper.updateByPrimaryKeySelective(keyWord);
        return BaseRespVo.ok();
    }

    @Override
    public BaseRespVo delete(KeyWord keyWord) {
        keyWordMapper.deleteByPrimaryKey(keyWord.getId());
        return BaseRespVo.ok();
    }
}
