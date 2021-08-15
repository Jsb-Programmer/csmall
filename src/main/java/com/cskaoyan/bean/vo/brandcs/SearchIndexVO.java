package com.cskaoyan.bean.vo.brandcs;

import com.cskaoyan.bean.pojo.Keyword;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SearchIndexVO
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/14 18:06
 * @Version 1.0
 **/
@Data
public class SearchIndexVO {
    //热门关键字列表第一条？？？
    Keyword defaultKeyword;
    //热门关键词列表
    List<Keyword> hotKeywordList;
    //历史关键字列表
    List<Keyword> historyKeywordList;
}
