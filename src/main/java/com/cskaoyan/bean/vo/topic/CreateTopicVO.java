package com.cskaoyan.bean.vo.topic;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author yangbo
 * @description
 * @date 2021/8/12 0:25
 */
@Data
public class CreateTopicVO {


    /**
     * id : 321
     * title : 123
     * subtitle : 123456
     * price : 123
     * readCount : 123456
     * goods : []
     * addTime : 2021-08-12 00:22:39
     * updateTime : 2021-08-12 00:22:39
     * content : <p>123456</p>
     */

    private int id;
    private String title;
    private String subtitle;
    private BigDecimal price;
    private String readCount;
    private Date addTime;
    private Date updateTime;
    private String content;
    private String[] goods;


}
