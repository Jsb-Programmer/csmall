package com.cskaoyan.bean.bo.topic;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * @author yangbo
 * @description
 * @date 2021/8/16 17:42
 */
@Data
public class CreateTopicBO {

    /**
     * subtitle : 1
     * content : <p><img src="http://182.92.235.201:8083/wx/storage/fetch/v41uv5603b36nvv0phwb.jpg" alt="" width="1440" height="1080" /></p>
     * price : 1
     * goods : []
     * title : 1
     */

    @NotNull(message = "当前专题子标题不能为空")
    private String subtitle;

    @NotNull(message = "当前专题内容不能为空")
    private String content;

    @NotNull(message = "当前价格不能为空")
    @Digits(integer = 10, fraction = 4, message = "当前价格要为数字")
    private String price;

    @NotNull(message = "当前专题标题不能为空")
    private String title;

    private String[] goods;



}
