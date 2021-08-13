package com.cskaoyan.bean.bo.comment;

import lombok.Data;

@Data
public class DeleteCommentBO {

    /**
     * id : 517
     * valueId : 1020000
     * type : 0
     * content : 简直不能太舒服，开车太实用了
     * userId : 1
     * hasPicture : false
     * picUrls : []
     * star : 1
     * addTime : 2018-02-01 00:00:00
     * updateTime : 2018-02-01 00:00:00
     * deleted : false
     */

    private Integer id;
    private Integer valueId;
    private Byte type;
    private String content;
    private Integer userId;
    private Boolean hasPicture;
    private Short star;
    private String addTime;
    private String updateTime;
    private Boolean deleted;
    private String[] picUrls;
}
