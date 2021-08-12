package com.cskaoyan.bean.bo.topic;

import lombok.Data;

/**
 * @author yangbo
 * @description
 * @date 2021/8/11 23:52
 */
@Data
public class CreateTopicBO {
    private String subtitle;
    private String content;
    private String price;
    private String readCount;
    private String[] goods;
    private String title;
}
