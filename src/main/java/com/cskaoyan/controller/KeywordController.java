package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.Keyword;
import com.cskaoyan.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mall
 * @description: 关键词
 * @author: Liu
 * @create: 2021-08-12 21:32
 **/
@RestController
@RequestMapping("admin/keyword")
public class KeywordController {

    @Autowired
    KeywordService keywordService;

    /**
     * 显示关键词list
     */
    @RequestMapping("list")
    public BaseRespVo keywordList(BaseParam baseParam, String keyword, String url) {
        BaseRespData data = keywordService.getKeywordList(baseParam, keyword, url);
        return BaseRespVo.ok(data);
    }

    /**
     * 新增关键字
     */
    @RequestMapping("create")
    public BaseRespVo createKeyword (@RequestBody Keyword keyword) {
        Keyword respKeyword = keywordService.createKeyword(keyword);
        return BaseRespVo.ok(respKeyword);
    }


    /**
     * 修改关键字
     */
    @RequestMapping("update")
    public BaseRespVo updateKeyword (@RequestBody Keyword keyword) {
        Keyword respKeyword = keywordService.updateKeyword(keyword);
        return BaseRespVo.ok(respKeyword);
    }

    /**
     * 删除关键字
     */
    @RequestMapping("delete")
    public BaseRespVo deleteKeyword (@RequestBody Keyword keyword) {
        int affectRows = keywordService.deleteKeyword(keyword);
        if (affectRows == 1) {
            return BaseRespVo.ok();
        }
        return BaseRespVo.fail("删除失败，请联系管理员");
    }

}
