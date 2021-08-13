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
public class WxConfigVO implements Serializable {

    /**
     * cskaoyanmall_wx_share : true
     * cskaoyanmall_wx_index_brand : 5
     * cskaoyanmall_wx_index_topic : 6
     * cskaoyanmall_wx_index_hot : 3
     * cskaoyanmall_wx_catlog_goods : 9
     * cskaoyanmall_wx_catlog_list : 7
     * cskaoyanmall_wx_index_new : 10
     */

    private String cskaoyanmall_wx_share;
    private String cskaoyanmall_wx_index_brand;
    private String cskaoyanmall_wx_index_topic;
    private String cskaoyanmall_wx_index_hot;
    private String cskaoyanmall_wx_catlog_goods;
    private String cskaoyanmall_wx_catlog_list;
    private String cskaoyanmall_wx_index_new;
}
