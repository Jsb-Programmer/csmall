package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.KeyWord;

public interface KeyWordService {
    BaseRespData querry(BaseParam param,String keyword,String url);

    BaseRespVo create(KeyWord keyWord);
    BaseRespVo update(KeyWord keyWord);
    BaseRespVo delete(KeyWord keyWord);
}
