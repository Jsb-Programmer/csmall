package com.cskaoyan.service.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.pojo.Keyword;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-12 21:33
 **/
public interface KeywordService {
    BaseRespData getKeywordList(BaseParam baseParam, String keyword, String url);

    Keyword createKeyword(Keyword keyword);

    Keyword updateKeyword(Keyword keyword);

    int deleteKeyword(Keyword keyword);
}
