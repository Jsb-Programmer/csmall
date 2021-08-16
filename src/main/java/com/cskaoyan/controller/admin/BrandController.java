package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.bo.market.BrandCreateBO;
import com.cskaoyan.bean.bo.market.BrandDeleteBO;
import com.cskaoyan.bean.bo.market.BrandUpdateBO;
import com.cskaoyan.bean.vo.market.*;
import com.cskaoyan.bean.bo.market.BaseParamBO;
import com.cskaoyan.service.admin.BrandService;
import com.cskaoyan.utils.ValidationUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @RequiresPermissions("admin:brand:list")
    @GetMapping("list")
    public BaseRespVo brandList(Integer id,String name,BaseParamBO baseParamBO){
        BaseRespDataVO baseRespDataVO = brandService.brandList(id,name,baseParamBO);
        return BaseRespVo.ok(baseRespDataVO);
    }

    /**
     * 添加制造商
     * @param brandCreateBO
     * @return
     */
    @RequiresPermissions("admin:brand:create")
    @PostMapping("create")
    public BaseRespVo brandCreate(@RequestBody @Validated BrandCreateBO brandCreateBO, BindingResult bindingResult){
//        //通过ExceptionHandler去处理异常
//        int i = Integer.parseInt(brandCreateBO.getFloorPrice());
        String s = ValidationUtil.dealWithFieldError(bindingResult);
        if (s != null){
            return BaseRespVo.fail(s);
        }
        BrandCreateVO brandCreateVO = brandService.brandCreate(brandCreateBO);
        return BaseRespVo.ok(brandCreateVO);
    }

    /**
     * 修改品牌制造商
     * @param brandUpdateBO
     * @return
     */
    @RequiresPermissions("admin:brand:update")
    @PostMapping("update")
    public BaseRespVo brandUpdate(@RequestBody BrandUpdateBO brandUpdateBO){
        BrandUpdateVO brandUpdateVO = brandService.brandUpdate(brandUpdateBO);
        return BaseRespVo.ok(brandUpdateVO);
    }

    /**
     * 删除品牌制造商
     * @param brandDeleteBO
     * @return
     */
    @RequiresPermissions("admin:brand:delete")
    @PostMapping("delete")
    public BaseRespVo brandDelete(@RequestBody BrandDeleteBO brandDeleteBO){
        brandService.brandDelete(brandDeleteBO);
        return BaseRespVo.ok();
    }
}
