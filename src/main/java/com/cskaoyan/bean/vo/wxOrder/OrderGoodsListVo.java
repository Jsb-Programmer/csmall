package com.cskaoyan.bean.vo.wxOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-14 16:34
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderGoodsListVo {
    private int number;
    private String picUrl;
    private int id;
    private String goodsName;

}
