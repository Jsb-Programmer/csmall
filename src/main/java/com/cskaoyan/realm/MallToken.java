package com.cskaoyan.realm;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-14 15:53
 **/

@Data
public class MallToken extends UsernamePasswordToken {
    String type;

    public MallToken(String username, String password, String type) {
        super(username, password);
        this.type = type;
    }
}
