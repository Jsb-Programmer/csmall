package com.cskaoyan.bean.vo.stat;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: mall
 * @description: 订单统计
 * @author: Liu
 * @create: 2021-08-12 18:48
 **/
@Data
public class StatOrderVO implements Serializable {


    private List<String> columns;
    private List<RowsOrderBean> rows;


}
