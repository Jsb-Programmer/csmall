package com.cskaoyan.bean.vo.market;

import lombok.Data;

/**
 * @ClassName CategoryListChildrenVO
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/12 17:30
 * @Version 1.0
 **/
@Data
public class CategoryListChildrenVO {
    /**
     * id : 1005007
     * name : 锅具
     * keywords :
     * desc : 一口好锅，炖煮生活一日三餐
     * iconUrl : http://yanxuan.nosdn.127.net/4aab4598017b5749e3b63309d25e9f6b.png
     * picUrl : http://yanxuan.nosdn.127.net/d2db0d1d0622c621a8aa5a7c06b0fc6d.png
     * level : L2
     */

    private int id;
    private String name;
    private String keywords;
    private String desc;
    private String iconUrl;
    private String picUrl;
    private String level;
    private Integer pid;
}
