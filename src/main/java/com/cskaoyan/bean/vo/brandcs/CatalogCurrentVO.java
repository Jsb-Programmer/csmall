package com.cskaoyan.bean.vo.brandcs;

import com.cskaoyan.bean.pojo.Category;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CatalogCurrentVO
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/14 11:27
 * @Version 1.0
 **/
@Data
public class CatalogCurrentVO {
    Category currentCategory;
    List<Category> currentSubCategory;


}
