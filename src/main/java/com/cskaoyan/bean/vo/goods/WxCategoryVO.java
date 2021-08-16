package com.cskaoyan.bean.vo.goods;

import com.cskaoyan.bean.pojo.Category;
import lombok.Data;

import java.util.List;

@Data
public class WxCategoryVO {
    private Category currentCategory;
    private List<Category> brotherCategory;
    private Category parentCategory;
}
