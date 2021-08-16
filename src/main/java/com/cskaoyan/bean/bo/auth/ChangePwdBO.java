package com.cskaoyan.bean.bo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-16 09:35
 **/

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ChangePwdBO {

    private String oldPassword;
    private String newPassword;
    private String newPassword2;
}
