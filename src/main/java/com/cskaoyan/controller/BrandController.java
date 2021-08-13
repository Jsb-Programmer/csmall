package com.cskaoyan.controller;

import com.cskaoyan.bean.bo.market.BrandCreateBO;
import com.cskaoyan.bean.bo.market.BrandDeleteBO;
import com.cskaoyan.bean.bo.market.BrandUpdateBO;
import com.cskaoyan.bean.vo.market.*;
import com.cskaoyan.bean.bo.market.BaseParamBO;
import com.cskaoyan.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BrandController
 * @Description 品牌制造商
 * @Author 王昀昊
 * @Date 2021/8/11 23:20
 * @Version 1.0
 **/
@RestController
@RequestMapping("admin/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    /**
     * 回显全部制造商
     * @param id
     * @param name
     * @param baseParamBO
     * @return
     */
    @RequestMapping("list")
    public BaseRespVo brandList(Integer id,String name,BaseParamBO baseParamBO){
        BaseRespDataVO baseRespDataVO = brandService.brandList(id,name,baseParamBO);
        return BaseRespVo.ok(baseRespDataVO);
    }

    /**
     * 添加制造商
     * @param brandCreateBO
     * @return
     */
    @RequestMapping("create")
    public BaseRespVo brandCreate(@RequestBody BrandCreateBO brandCreateBO){
        BrandCreateVO brandCreateVO = brandService.brandCreate(brandCreateBO);
        return BaseRespVo.ok(brandCreateVO);
    }

    /**
     * 修改品牌制造商
     * @param brandUpdateBO
     * @return
     */
    @RequestMapping("update")
    public BaseRespVo brandUpdate(@RequestBody BrandUpdateBO brandUpdateBO){
        BrandUpdateVO brandUpdateVO = brandService.brandUpdate(brandUpdateBO);
        return BaseRespVo.ok(brandUpdateVO);
    }

    /**
     * 删除品牌制造商
     * @param brandDeleteBO
     * @return
     */
    @RequestMapping("delete")
    public BaseRespVo brandDelete(@RequestBody BrandDeleteBO brandDeleteBO){
        brandService.brandDelete(brandDeleteBO);
        return BaseRespVo.ok();
    }
}
