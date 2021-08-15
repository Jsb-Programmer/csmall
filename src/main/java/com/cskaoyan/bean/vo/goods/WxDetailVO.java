package com.cskaoyan.bean.vo.goods;

import com.cskaoyan.bean.pojo.*;
import lombok.Data;

import java.util.List;

@Data
public class WxDetailVO {
    private List<SpecificationVO> specificationList;
    private List<Groupon> groupon;
    private List<Issue> issue;
    private Integer userHasCollect;
    private String shareImage;
    private CommentVO comment;
    private List<GoodsAttribute> attribute;
    private Brand brand;
    private List<GoodsProduct> productList;
    private Goods info;
}
