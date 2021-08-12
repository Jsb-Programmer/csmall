package com.cskaoyan.bean.vo.market;

import lombok.Data;

/**
 * @ClassName CategoryCreateVO
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/12 19:17
 * @Version 1.0
 **/
@Data
public class CategoryCreateVO {

    /**
     * id : 1036054
     * name : 女仆
     * keywords : 乖巧
     * desc : 你值得拥有
     * pid : 0
     * iconUrl : http://182.92.235.201:8083/wx/storage/fetch/8bp2f668n8aoppllvv65.jpg
     * picUrl : http://182.92.235.201:8083/wx/storage/fetch/z0k6cwbekzq9g5z40e8l.jpg
     * level : L1
     * addTime : 2021-08-12 19:12:14
     * updateTime : 2021-08-12 19:12:14
     */

    private Integer id;
    private String name;
    private String keywords;
    private String desc;
    private Integer pid;
    private String iconUrl;
    private String picUrl;
    private String level;
    private String addTime;
    private String updateTime;


}
