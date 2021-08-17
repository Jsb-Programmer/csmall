package com.cskaoyan.bean.bo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-16 23:27
 **/

@NoArgsConstructor
@Data
@AllArgsConstructor
public class RegisterUserBO {

    private String username;
    private String password;
    private String mobile;
    private String code;
    private String wxCode;
}
