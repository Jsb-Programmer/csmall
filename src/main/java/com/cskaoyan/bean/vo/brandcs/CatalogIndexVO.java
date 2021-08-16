package com.cskaoyan.bean.vo.brandcs;

import com.cskaoyan.bean.pojo.Category;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CatalogIndexVO
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/14 14:36
 * @Version 1.0
 **/
@Data
public class CatalogIndexVO {

    //当前一级类目
    Category currentCategory;
    //所有一级类目
    List<Category> categoryList;
    //当前类目下的所有二级类目
    List<Category> currentSubCategory;
}
