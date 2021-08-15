package com.cskaoyan.bean.vo.goods;

import com.cskaoyan.bean.pojo.GoodsSpecification;
import lombok.Data;

import java.util.List;

@Data
public class SpecificationVO {
    private String name;
    private List<GoodsSpecification> valueList;
}
