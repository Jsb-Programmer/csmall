package com.cskaoyan.bean.bo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-17 00:39
 **/


@NoArgsConstructor
@Data
@AllArgsConstructor
public class ResetBO {
    private String mobile;
    private String code;
    private String password;

}
