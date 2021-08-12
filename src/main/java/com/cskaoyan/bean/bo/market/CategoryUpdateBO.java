package com.cskaoyan.bean.bo.market;

import lombok.Data;

import java.util.List;

/**
 * @ClassName CategoryUpdateBO
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/12 20:24
 * @Version 1.0
 **/
@Data
public class CategoryUpdateBO {

    /**
     * id : 1036056
     * name : 哈哈哈哈哈哈和
     * keywords : 哈哈哈哈哈哈
     * desc : 哈哈哈哈哈哈哈
     * iconUrl :
     * picUrl :
     * level : L1
     * children : [{"id":1036061,"name":"呵呵呵呵呵","keywords":"呵呵呵呵呵","desc":"呵呵呵呵呵","iconUrl":"","picUrl":"","level":"L2"}]
     * pid : 0
     */

    private Integer id;
    private String name;
    private String keywords;
    private String desc;
    private String iconUrl;
    private String picUrl;
    private String level;
    private Integer pid;
    private List<CategoryUpdateChildrenBO> children;

}
