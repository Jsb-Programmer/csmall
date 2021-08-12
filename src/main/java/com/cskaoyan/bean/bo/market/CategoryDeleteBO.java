package com.cskaoyan.bean.bo.market;

import lombok.Data;

import java.util.List;

/**
 * @ClassName CategoryDeleteBO
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/12 20:56
 * @Version 1.0
 **/
@Data
public class CategoryDeleteBO {

    /**
     * id : 1036056
     * name : 哈哈哈哈哈哈和
     * keywords : 哈哈哈哈哈哈
     * desc : 哈哈哈哈哈哈哈
     * iconUrl :
     * picUrl :
     * level : L1
     * children : []
     */

    private int id;
    private String name;
    private String keywords;
    private String desc;
    private String iconUrl;
    private String picUrl;
    private String level;
    private List<CategoryUpdateChildrenBO> children;


}
