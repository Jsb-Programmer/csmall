package com.cskaoyan.bean.vo.stat;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @program: mall
 * @description: 用户统计
 * @author: Liu
 * @create: 2021-08-12 18:48
 **/
@Data
public class StatUserVO implements Serializable {

    private List<String> columns;
    private List<RowsUserBean> rows;


}
