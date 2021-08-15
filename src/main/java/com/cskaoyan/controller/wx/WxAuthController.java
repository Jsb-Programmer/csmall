package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.LoginUser;
import com.cskaoyan.bean.bo.user.NullBO;
import com.cskaoyan.bean.bo.user.WxLogoutMsg;
import com.cskaoyan.bean.bo.user.WxUserLoginBO;
import com.cskaoyan.realm.MallToken;
import com.cskaoyan.service.admin.UserService;
import com.cskaoyan.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-14 14:46
 **/

//@RestController
@RequestMapping("wx/auth")
public class WxAuthController {

    @Autowired
    UserService userService;

    @RequestMapping("login_by_weixin")
    public BaseRespVo wxLogin(){
        return null;
    }

    @RequestMapping("login")
    public BaseRespVo accountLogin(@RequestBody LoginUser loginUser) throws Exception {
        //"yyyy-MM-dd'T'HH:mm:ss.SSS"
        MallToken mallToken = new MallToken(loginUser.getUsername(),
                MD5Utils.encrypt(loginUser.getPassword()),"wx");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(mallToken);
        }catch (Exception e){
            return BaseRespVo.fail("账号密码不对",700);
        }

        WxUserLoginBO wxUserLoginBO = userService.userLoginInfo(loginUser.getUsername(),loginUser.getPassword());

        return BaseRespVo.ok(wxUserLoginBO);
    }

    @RequestMapping("logout")
    public WxLogoutMsg logout(@RequestBody NullBO nullBO){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            subject.logout();
            return WxLogoutMsg.logout();

        }
        return WxLogoutMsg.fail();
    }

}
