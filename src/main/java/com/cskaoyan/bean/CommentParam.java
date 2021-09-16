package com.cskaoyan.bean;

import lombok.Data;

@Data
public class CommentParam extends BaseParam {
    private String userId;
    private String valueId;
}
