package com.cskaoyan.bean.bo.user;

import lombok.Data;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-15 00:42
 **/

@Data
public class WxLogoutMsg {
    //{"errno":501,"errmsg":"请登录"}
    Integer errno;
    String errmsg;

    public static WxLogoutMsg logout(){
        WxLogoutMsg wxLogoutMsg = new WxLogoutMsg();
        wxLogoutMsg.setErrno(501);
        wxLogoutMsg.setErrmsg("请登录");
        return wxLogoutMsg;
    }
}
