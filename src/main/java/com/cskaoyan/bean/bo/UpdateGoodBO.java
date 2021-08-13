package com.cskaoyan.bean.bo;

import com.cskaoyan.bean.pojo.GoodsAttribute;

import com.cskaoyan.bean.pojo.GoodsSpecification;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class UpdateGoodBO {
    @Valid
    private UpdateGoodBeanBO goods;
    private List<GoodsSpecification> specifications;
    private List<@Valid UpdateGoodsProductBO> products;
    private List<GoodsAttribute> attributes;
}
