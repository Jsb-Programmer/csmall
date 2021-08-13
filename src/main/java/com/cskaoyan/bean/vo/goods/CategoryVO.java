package com.cskaoyan.bean.vo.goods;

import lombok.Data;

import java.util.List;

@Data
public class CategoryVO {

        /**
         * value : 1005001
         * label : 餐厨
         * children : [{"value":1005007,"label":"锅具"},{"value":1005008,"label":"餐具"},{"value":1005009,"label":"清洁"},{"value":1007000,"label":"杯壶"},{"value":1008011,"label":"清洁保鲜"},{"value":1008012,"label":"功能厨具"},{"value":1008013,"label":"茶具咖啡具"},{"value":1013005,"label":"刀剪砧板"},{"value":1020003,"label":"服饰"},{"value":1023000,"label":"厨房小电"},{"value":1036038,"label":"112"},{"value":1036045,"label":"11"}]
         */

        private Integer value;
        private String label;
        private List<CategoryChildrenVO> children;

}
