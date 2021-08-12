package com.cskaoyan.bean.bo.market;

import lombok.Data;

/**
 * @ClassName CategoryCreateBO
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/12 19:17
 * @Version 1.0
 **/
@Data
public class CategoryCreateBO {

    /**
     * name : 女仆
     * keywords : 乖巧
     * level : L1
     * pid : 0
     * desc : 你值得拥有
     * iconUrl : http://182.92.235.201:8083/wx/storage/fetch/8bp2f668n8aoppllvv65.jpg
     * picUrl : http://182.92.235.201:8083/wx/storage/fetch/z0k6cwbekzq9g5z40e8l.jpg
     */

    private String name;
    private String keywords;
    private String level;
    private Integer pid;
    private String desc;
    private String iconUrl;
    private String picUrl;

}
