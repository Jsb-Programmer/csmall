package com.cskaoyan.service.admin;

import com.cskaoyan.bean.bo.market.CategoryCreateBO;
import com.cskaoyan.bean.bo.market.CategoryDeleteBO;
import com.cskaoyan.bean.bo.market.CategoryUpdateBO;
import com.cskaoyan.bean.vo.market.CategoryCreateVO;
import com.cskaoyan.bean.vo.market.CategoryL1VO;
import com.cskaoyan.bean.vo.market.CategoryListVO;

import java.util.List;

public interface CategoryService {
    List<CategoryListVO> categoryList();

    CategoryCreateVO categoryCreate(CategoryCreateBO categoryCreateBO);

    List<CategoryL1VO> categoryL1();

    void categoryUpdate(CategoryUpdateBO categoryUpdateBO);

    void categoryDelete(CategoryDeleteBO categoryDeleteBO);
}
