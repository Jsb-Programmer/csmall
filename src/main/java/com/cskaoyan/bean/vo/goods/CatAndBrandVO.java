package com.cskaoyan.bean.vo.goods;

import lombok.Data;

import java.util.List;

@Data
public class CatAndBrandVO {
    private List<CategoryVO> categoryList;
    private List<BrandVO> brandList;
}
