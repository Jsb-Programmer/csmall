package com.cskaoyan.bean.vo.goods;

import com.cskaoyan.bean.pojo.Category;
import com.cskaoyan.bean.pojo.Goods;
import lombok.Data;

import java.util.List;
@Data
public class WxGoodsListVO {
    private List<Goods> goodsList;
    private Long count;
    private List<Category> filterCategoryList;
}
