package com.cskaoyan.bean.bo.wxOrder;

import com.cskaoyan.bean.BaseRespVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-14 21:52
 **/
@Data
public class OrderCommentBo implements Serializable {

    /**
     * orderGoodsId : 16
     * content : 好用！
     * star : 4
     * hasPicture : false
     * picUrls : []
     */

    private Integer orderGoodsId;
    private String content;
    private Short star;
    private Boolean hasPicture;
    private String[] picUrls;
}
