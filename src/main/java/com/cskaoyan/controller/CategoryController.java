package com.cskaoyan.controller;

import com.cskaoyan.bean.bo.market.CategoryCreateBO;
import com.cskaoyan.bean.bo.market.CategoryDeleteBO;
import com.cskaoyan.bean.bo.market.CategoryUpdateBO;
import com.cskaoyan.bean.vo.market.BaseRespVo;
import com.cskaoyan.bean.vo.market.CategoryCreateVO;
import com.cskaoyan.bean.vo.market.CategoryL1VO;
import com.cskaoyan.bean.vo.market.CategoryListVO;
import com.cskaoyan.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("list")
    public BaseRespVo categoryList(){
        List<CategoryListVO> categoryListVOS = categoryService.categoryList();
        return BaseRespVo.ok(categoryListVOS);
    }

    @RequestMapping("create")
    public BaseRespVo categoryCreate(@RequestBody CategoryCreateBO categoryCreateBO){
        CategoryCreateVO categoryCreateVO = categoryService.categoryCreate(categoryCreateBO);
        return BaseRespVo.ok(categoryCreateVO);
    }

    @RequestMapping("l1")
    public BaseRespVo categoryL1(){
        List<CategoryL1VO> categoryL1VOList = categoryService.categoryL1();
        return BaseRespVo.ok(categoryL1VOList);
    }

    @RequestMapping("update")
    public BaseRespVo categoryUpdate(@RequestBody CategoryUpdateBO categoryUpdateBO){
        categoryService.categoryUpdate(categoryUpdateBO);
        return BaseRespVo.ok();
    }

    @RequestMapping("delete")
    public BaseRespVo categoryDelete(@RequestBody CategoryDeleteBO categoryDeleteBO){
        categoryService.categoryDelete(categoryDeleteBO);
        return BaseRespVo.ok();
    }
}
