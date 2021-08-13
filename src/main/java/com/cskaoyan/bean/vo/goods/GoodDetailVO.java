package com.cskaoyan.bean.vo.goods;

import com.cskaoyan.bean.pojo.GoodsAttribute;
import com.cskaoyan.bean.pojo.GoodsProduct;
import com.cskaoyan.bean.pojo.GoodsSpecification;
import lombok.Data;

import java.util.List;
@Data
public class GoodDetailVO {

    /**
     * categoryIds : [1010000,1005000]
     * goods : {"id":1181022,"goodsSn":"1234323","name":"1223","categoryId":1005000,"brandId":1001008,"gallery":["http://182.92.235.201:8083/wx/storage/fetch/54y7if0bmxsujuz4kugp.jpg","http://182.92.235.201:8083/wx/storage/fetch/dnp0mwiduwz98q1pu9si.jpg"],"keywords":"请问","brief":"777A","isOnSale":false,"sortOrder":100,"picUrl":"http://182.92.235.201:8083/wx/storage/fetch/57ful3rtb7hfbj53tuih.jpg","isNew":false,"isHot":true,"unit":"个","counterPrice":100,"retailPrice":1000,"addTime":"2021-08-12 20:18:29","updateTime":"2021-08-12 20:18:29","deleted":false}
     * attributes : [{"id":1069,"goodsId":1181022,"attribute":"qerf","value":"100","addTime":"2021-08-12 20:18:29","updateTime":"2021-08-12 20:18:29","deleted":false}]
     * specifications : [{"id":356,"goodsId":1181022,"specification":"规格","value":"标准","picUrl":"","addTime":"2021-08-12 20:18:29","updateTime":"2021-08-12 20:18:29","deleted":false}]
     * products : [{"id":328,"goodsId":1181022,"specifications":["标准"],"price":100,"number":100,"url":"http://182.92.235.201:8083/wx/storage/fetch/77zajh21wcxq9tkhyi7j.jpg","addTime":"2021-08-12 20:18:29","updateTime":"2021-08-12 20:18:29","deleted":false}]
     */

    private GoodsVO goods;
    private Integer[] categoryIds;
    private List<GoodsAttribute> attributes;
    private List<GoodsSpecification> specifications;
    private List<GoodsProduct> products;

}
