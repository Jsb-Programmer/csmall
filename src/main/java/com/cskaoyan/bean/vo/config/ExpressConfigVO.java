package com.cskaoyan.bean.vo.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-12 15:23
 **/
@Data
public class ExpressConfigVO implements Serializable {

    /**
     * cskaoyanmall_express_freight_value : 8
     * cskaoyanmall_express_freight_min : 9
     */

    private String cskaoyanmall_express_freight_value;
    private String cskaoyanmall_express_freight_min;
}
