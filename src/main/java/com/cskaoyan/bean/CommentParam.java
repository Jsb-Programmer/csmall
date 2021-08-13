package com.cskaoyan.bean;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class CommentParam extends BaseParam {
    @Min(value = 0, message = "用户ID应该为数字")
    private String userId;
    @Min(value = 0, message = "商品ID应该为数字")
    private String valueId;
}
