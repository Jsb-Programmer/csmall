package com.cskaoyan.bean.bo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-12 20:12
 **/

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ReceivedAddressBO {

    private String area;
    private Boolean isDefault;
    private Integer areaId;
    private String address;
    private String province;
    private String city;
    private String name;
    private String mobile;
    private Integer id;
    private Integer cityId;
    private Integer userId;
    private Integer provinceId;

}
