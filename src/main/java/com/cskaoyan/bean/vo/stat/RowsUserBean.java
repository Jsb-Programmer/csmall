package com.cskaoyan.bean.vo.stat;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-12 20:37
 **/

@Data
public  class RowsUserBean implements Serializable {
    /**
     * users : 3
     */
    private Date day;
    private int users;
}

