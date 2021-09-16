package com.cskaoyan.bean.vo.goods;

import lombok.Data;

@Data
public class CommentDataVO {
    private String addTime;
    private String[] picList;
    private String nickname;
    private Integer id;
    private String avatar;
    private String content;
}
