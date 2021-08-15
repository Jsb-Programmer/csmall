package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.SearchHistory;
import com.cskaoyan.bean.vo.brandcs.SearchIndexVO;
import com.cskaoyan.service.wx.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    public BaseRespVo searchIndex(){
        SearchIndexVO searchIndexVO = searchHistoryService.searchIndex();
        return BaseRespVo.ok(searchIndexVO);
    }

    @RequestMapping("helper")
    public BaseRespVo searchHelper(){
//        searchHistoryService.searchHelper();
        return BaseRespVo.ok(new ArrayList<>());
    }

    @RequestMapping("clearhistory")
    public BaseRespVo searchClearhistory(){
        searchHistoryService.searchClearhistory();
        return BaseRespVo.ok();
    }
}
