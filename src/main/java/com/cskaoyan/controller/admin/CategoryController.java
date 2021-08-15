package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.bo.market.CategoryCreateBO;
import com.cskaoyan.bean.bo.market.CategoryDeleteBO;
import com.cskaoyan.bean.bo.market.CategoryUpdateBO;
import com.cskaoyan.bean.vo.market.BaseRespVo;
import com.cskaoyan.bean.vo.market.CategoryCreateVO;
import com.cskaoyan.bean.vo.market.CategoryL1VO;
import com.cskaoyan.bean.vo.market.CategoryListVO;
import com.cskaoyan.service.admin.CategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @ClassName CategoryController
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/12 17:05
 * @Version 1.0
 **/
@RestController
@RequestMapping("admin/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequiresPermissions("admin:category:list")
    @GetMapping("list")
    public BaseRespVo categoryList(){
        List<CategoryListVO> categoryListVOS = categoryService.categoryList();
        return BaseRespVo.ok(categoryListVOS);
    }

    @RequiresPermissions("admin:category:create")
    @PostMapping("create")
    public BaseRespVo categoryCreate(@RequestBody CategoryCreateBO categoryCreateBO){
        CategoryCreateVO categoryCreateVO = categoryService.categoryCreate(categoryCreateBO);
        return BaseRespVo.ok(categoryCreateVO);
    }


    @GetMapping("l1")
    public BaseRespVo categoryL1(){
        List<CategoryL1VO> categoryL1VOList = categoryService.categoryL1();
        return BaseRespVo.ok(categoryL1VOList);
    }

    @RequiresPermissions("admin:category:update")
    @PostMapping("update")
    public BaseRespVo categoryUpdate(@RequestBody CategoryUpdateBO categoryUpdateBO){
        categoryService.categoryUpdate(categoryUpdateBO);
        return BaseRespVo.ok();
    }

    @RequiresPermissions("admin:category:delete")
    @PostMapping("delete")
    public BaseRespVo categoryDelete(@RequestBody CategoryDeleteBO categoryDeleteBO){
        categoryService.categoryDelete(categoryDeleteBO);
        return BaseRespVo.ok();
    }
}
