package com.cskaoyan.bean.bo.user;

import com.cskaoyan.bean.vo.user.UserInfoVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-14 14:59
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxUserLoginBO {
    UserInfoVO userInfo;
    String tokenExpire;
    String token;
}
