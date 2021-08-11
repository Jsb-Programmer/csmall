package com.cskaoyan.bean;

import lombok.Data;

import java.util.List;

@Data
public class InfoData {
    /**
     * roles : ["超级管理员"]
     * name : admin123
     * perms : ["*"]
     * avatar : https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif
     */

    private String name;
    private String avatar;
    private List<String> roles;
    private List<String> perms;

    //GsonFormat 👉 根据Json数据
    // 格式化，创建对应的成员变量
    //                    成员变量类型
    //                    成员变量名
    //alt + s

}
