package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.brandcs.CatalogCurrentVO;
import com.cskaoyan.bean.vo.brandcs.CatalogIndexVO;
import com.cskaoyan.service.wx.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CatalogController
 * @Description 类目
 * @Author 王昀昊
 * @Date 2021/8/14 11:11
 * @Version 1.0
 **/
@RestController
@RequestMapping("wx/catalog")
public class CatalogController {

    @Autowired
    CatalogService catalogService;

    @RequestMapping("current")
    public BaseRespVo catalogCurrent(Integer id){
        CatalogCurrentVO catalogCurrentVO = catalogService.catalogCurrent(id);
        return BaseRespVo.ok(catalogCurrentVO);
    }

    @RequestMapping("index")
    public BaseRespVo catalogIndex(){
        CatalogIndexVO catalogIndexVO = catalogService.catalogIndex();
        return BaseRespVo.ok(catalogIndexVO);
    }
}
