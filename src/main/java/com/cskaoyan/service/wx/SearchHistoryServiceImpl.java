package com.cskaoyan.service.wx;

import com.cskaoyan.bean.pojo.*;
import com.cskaoyan.bean.vo.brandcs.SearchIndexChildVO;
import com.cskaoyan.bean.vo.brandcs.SearchIndexVO;
import com.cskaoyan.mapper.KeyFestivalMapper;
import com.cskaoyan.mapper.KeywordMapper;
import com.cskaoyan.mapper.SearchHistoryMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    KeyFestivalMapper keyFestivalMapper;

    /**
     * 搜索索引
     * @param
     * @return
     */
    @Override
    public SearchIndexVO searchIndex(HttpServletRequest request) {
        SearchIndexVO searchIndexVO = new SearchIndexVO();
        //历史关键字列表
        //TODO
        // 需要获取 userId
        Subject subject = SecurityUtils.getSubject();
        Integer id = null;
        if (subject.isAuthenticated()) {
            id = (Integer) subject.getPrincipal();
        }
        List<SearchIndexChildVO> searchIndexChild2VOList = searchHistoryMapper.selectToKeyword(id);
        searchIndexVO.setHistoryKeywordList(searchIndexChild2VOList);
//        ------------------------------------------------------------
//        if (!subject.isAuthenticated()){
//            Cookie[] cookies = request.getCookies();
//            List<String> list = new ArrayList<>();
//            if (cookies != null){
//                for (Cookie cookie : cookies) {
//                    list.add(cookie.getValue());
//                }
//            }
//            List<Keyword> keywordList = new ArrayList<>();
//            if (list != null) {
//                for (String s : list) {
//                    Keyword keyword = new Keyword();
//                    keyword.setKeyword(s);
//                    keywordList.add(keyword);
//                }
//            }
//            searchIndexVO.setHotKeywordList(keywordList);
//        }else {
//            Integer id = (Integer) subject.getPrincipal();
//            List<SearchIndexChildVO> searchIndexChild2VOList = searchHistoryMapper.selectToKeyword(id);
//            searchIndexVO.setHistoryKeywordList(searchIndexChild2VOList);
//        }
//        -------------------------------------------------------------------------------------------------

        //热门关键词列表
        //付费商家显示的内容
        KeywordExample example = new KeywordExample();
        KeywordExample.Criteria criteria = example.createCriteria();
        criteria.andIsHotEqualTo(false);
        List<Keyword> keywordList = keywordMapper.selectByExample(example);
        //根据不同日期显示不同的关键词
        int time = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        KeyFestival keyFestival = keyFestivalMapper.selectByTime(time);
        Keyword keyword = new Keyword();
        keyword.setKeyword(keyFestival.getFestival());
        keywordList.add(keyword);
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
        Subject subject = SecurityUtils.getSubject();
        Integer primaryPrincipal = (Integer) subject.getPrincipal();
        criteria.andUserIdEqualTo(primaryPrincipal);
        searchHistoryMapper.updateByExampleSelective(searchHistory,example);
    }

    @Override
    public List<String> searchHelper(String keyword) {
        SearchHistoryExample example = new SearchHistoryExample();
        SearchHistoryExample.Criteria criteria = example.createCriteria();
        if (keyword != null && !"".equals(keyword)) {
            criteria.andKeywordLike("%" + keyword + "%");
        }
        List<SearchHistory> searchHistories = searchHistoryMapper.selectByExample(example);
        List keywordList = new ArrayList<String>();
        for (SearchHistory searchHistory : searchHistories) {
            keywordList.add(searchHistory.getKeyword());
        }
        return keywordList;
    }




}
