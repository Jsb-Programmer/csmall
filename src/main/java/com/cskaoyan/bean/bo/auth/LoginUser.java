package com.cskaoyan.bean.bo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-11 21:24
 **/

@NoArgsConstructor
@Data
@AllArgsConstructor
public class LoginUser {

    private String username;
    private String password;
}
