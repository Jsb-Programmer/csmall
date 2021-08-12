package com.cskaoyan.bean.vo.storage;

import lombok.Data;

@Data
public class ImgUploadVO {
    /**
     * id : 48
     * key : 6hdb3axfl5ch8d1a5elx.jpg
     * name : 永劫无间.jpg
     * type : image/jpeg
     * size : 43632
     * url : http://182.92.235.201:8083/wx/storage/fetch/6hdb3axfl5ch8d1a5elx.jpg
     * addTime : 2021-08-11 20:15:48
     * updateTime : 2021-08-11 20:15:48
     */

    private int id;
    private String key;
    private String name;
    private String type;
    private int size;
    private String url;
    private String addTime;
    private String updateTime;
}
