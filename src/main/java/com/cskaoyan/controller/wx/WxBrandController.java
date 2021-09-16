package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.Brand;
import com.cskaoyan.bean.vo.brandcs.WxBrandListVO;
import com.cskaoyan.bean.vo.brandcs.WxDrandDetailVO;
import com.cskaoyan.bean.vo.market.BaseRespDataVO;
import com.cskaoyan.bean.vo.market.BrandDeleteVO;
import com.cskaoyan.service.admin.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName WxBrandController
 * @Description 品牌
 * @Author 王昀昊
 * @Date 2021/8/15 13:56
 * @Version 1.0
 **/
@RestController
@RequestMapping("wx/brand")
public class WxBrandController {

    @Autowired
    BrandService brandService;

    @RequestMapping("list")
    public BaseRespVo brandList(Integer page,Integer size){
        WxBrandListVO wxBrandListVO = brandService.wxBrandList(page,size);
        return BaseRespVo.ok(wxBrandListVO);
    }

    @RequestMapping("detail")
    public BaseRespVo brandDetail(Integer id){
        WxDrandDetailVO wxDrandDetailVO = brandService.brandDetail(id);
        return BaseRespVo.ok(wxDrandDetailVO);
    }


}
