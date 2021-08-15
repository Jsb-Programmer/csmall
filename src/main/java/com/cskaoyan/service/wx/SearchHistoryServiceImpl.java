package com.cskaoyan.service.wx;

import com.cskaoyan.bean.pojo.*;
import com.cskaoyan.bean.vo.brandcs.SearchIndexChildVO;
import com.cskaoyan.bean.vo.brandcs.SearchIndexVO;
import com.cskaoyan.mapper.KeywordMapper;
import com.cskaoyan.mapper.SearchHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SearchHistoryServiceImpl
 * @Description 搜索
 * @Author 王昀昊
 * @Date 2021/8/14 20:11
 * @Version 1.0
 **/
@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {

    @Autowired
    SearchHistoryMapper searchHistoryMapper;
    @Autowired
    KeywordMapper keywordMapper;

    /**
     * 搜索索引
     * @return
     */
    @Override
    public SearchIndexVO searchIndex() {
        SearchIndexVO searchIndexVO = new SearchIndexVO();
        //历史关键字列表
        List<SearchIndexChildVO> searchIndexChild2VOList = searchHistoryMapper.selectToKeyword();

        //热门关键词列表
        KeywordExample keywordExample = new KeywordExample();
        List<Keyword> keywordList = keywordMapper.selectByExample(keywordExample);
        searchIndexVO.setHotKeywordList(keywordList);

        //热门关键词列表第一条
        searchIndexVO.setDefaultKeyword(keywordList.get(0));

        return searchIndexVO;
    }

    /**
     * 搜索清除历史记录
     */
    @Override
    public void searchClearhistory() {
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setDeleted(true);
        searchHistory.setUpdateTime(new Date());
        SearchHistoryExample example = new SearchHistoryExample();
        SearchHistoryExample.Criteria criteria = example.createCriteria();
        //TODO
        // 需要获取 userId
        criteria.andUserIdEqualTo(1);
        searchHistoryMapper.updateByExampleSelective(searchHistory,example);
    }
}
