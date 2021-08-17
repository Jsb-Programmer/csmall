package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.SearchHistory;
import com.cskaoyan.bean.vo.brandcs.SearchIndexVO;
import com.cskaoyan.service.wx.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName SearchController
 * @Description 搜索
 * @Author 王昀昊
 * @Date 2021/8/14 17:22
 * @Version 1.0
 **/
@RestController
@RequestMapping("wx/search")
public class SearchController {
    @Autowired
    SearchHistoryService searchHistoryService;

    @RequestMapping("index")
    public BaseRespVo searchIndex(HttpServletRequest request){
        SearchIndexVO searchIndexVO = searchHistoryService.searchIndex(request);
        return BaseRespVo.ok(searchIndexVO);
    }

    /**
     * 搜索框设置
     * @param keyword
     * @return
     */
    @RequestMapping("helper")
    public BaseRespVo searchHelper(String keyword){
        List<String> idList = searchHistoryService.searchHelper(keyword);
        return BaseRespVo.ok(idList);
    }

    @RequestMapping("clearhistory")
    public BaseRespVo searchClearhistory(){
        searchHistoryService.searchClearhistory();
        return BaseRespVo.ok();
    }
}
