package com.cskaoyan.bean.vo.goods;

import com.cskaoyan.bean.pojo.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentVO {
    private List<Comment> data;
    private Integer count;
}
