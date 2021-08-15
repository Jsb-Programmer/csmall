package com.cskaoyan.bean.vo.home;

import com.cskaoyan.bean.pojo.Goods;
import lombok.Data;

import java.util.List;

@Data
public class WxFloorGoodsVO {
    private String name;
    private List<Goods> goodsList;
    private Integer id;
}
