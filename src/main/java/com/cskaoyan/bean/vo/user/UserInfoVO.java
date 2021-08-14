package com.cskaoyan.bean.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-14 15:00
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO {
    String nickName;
    String avatarUrl;
}
