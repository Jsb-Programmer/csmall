package com.cskaoyan.bean.bo;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;


@Data
public class CreateGoodProductsBO {
    /**
     * id : 0
     * specifications : ["xxx","yyy"]
     * price : 12000
     * number : 9000
     * url : http://182.92.235.201:8083/wx/storage/fetch/1r2sulh327xoi81k393r.jpg
     */

    private Integer id;
    @Digits(integer = 10, fraction = 4, message = "货品售价要为数字")
    private String price;
    @Min(value = 0, message = "货品数量要为数字")
    private String number;
    private String url;
    private String[] specifications;
}