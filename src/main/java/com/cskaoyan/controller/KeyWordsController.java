package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.KeyWord;
import com.cskaoyan.service.KeyWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @ClassName KeyWordsController
 * @Description 关键词页面的显示，添加，修改 ，删除
 * @Author Programmer
 * @Date 2021/8/13 14:34
 **/
@RestController
@RequestMapping("admin/keyword")
public class KeyWordsController {
    @Autowired
    KeyWordService keyWordService;

    @RequestMapping("list")
    public BaseRespVo list(BaseParam param, String keyword, String url){
        BaseRespData data = keyWordService.querry(param, keyword, url);
        return BaseRespVo.ok(data);

    }
    @RequestMapping("create")
    public BaseRespVo create(@RequestBody KeyWord keyWord){
        BaseRespVo data = keyWordService.create(keyWord);
        return  BaseRespVo.ok(data);

    }
    @RequestMapping("update")
    public BaseRespVo update(@RequestBody KeyWord keyWord)
    {
        BaseRespVo data = keyWordService.update(keyWord);
        return BaseRespVo.ok(data);

    }
    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody KeyWord keyWord){
        BaseRespVo data = keyWordService.delete(keyWord);
        return BaseRespVo.ok(data);
    }
}
