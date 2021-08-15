package com.cskaoyan.bean.vo.home;

import com.cskaoyan.bean.pojo.*;
import lombok.Data;


import java.util.List;

@Data
public class WxIndexVO {
    private List<Goods> newGoodsList;
    private List<Coupon> couponList;
    private List<Category> channel;
    private List<WxGroupOnVO> grouponList;
    private List<Ad> banner;
    private List<Brand> brandList;
    private List<Goods> hotGoodsList;
    private List<Topic> topicList;
    private List<WxFloorGoodsVO> floorGoodsList;
}
