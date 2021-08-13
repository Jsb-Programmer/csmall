package com.cskaoyan.bean.vo.market;

import lombok.Data;

import java.util.List;

/**
 * @ClassName Province
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/11 19:06
 * @Version 1.0
 **/
@Data
public class Province {
    Integer id;
    Integer pid;
    String name;
    Byte type;
    Integer code;
    List<City> children;
}
