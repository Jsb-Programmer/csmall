package com.cskaoyan.bean.vo.goods;

import lombok.Data;

import java.util.List;

@Data
public class CommentVO {
    private List<CommentDataVO> data;
    private Integer count;
}
