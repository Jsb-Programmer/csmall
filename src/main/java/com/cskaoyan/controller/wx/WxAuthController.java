package com.cskaoyan.controller.wx;

import com.aliyuncs.exceptions.ClientException;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.LoginUser;
import com.cskaoyan.bean.bo.auth.RegisterUserBO;
import com.cskaoyan.bean.bo.auth.ResetBO;
import com.cskaoyan.bean.bo.user.WxLogoutMsg;
import com.cskaoyan.bean.bo.user.WxUserLoginBO;
import com.cskaoyan.config.SmsComponent;
import com.cskaoyan.realm.MallToken;
import com.cskaoyan.service.admin.UserService;
import com.cskaoyan.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-14 14:46
 **/

@RestController
@RequestMapping("wx/auth")
public class WxAuthController {

    @Autowired
    UserService userService;
    @Autowired
    SmsComponent smsComponent;

    static String code;
    static Date expiredTime;


   @RequestMapping("login_by_weixin")
    public String logIn(){
    return "chitu_qrcode.png";
}




    @RequestMapping("login")
    public BaseRespVo accountLogin(@RequestBody LoginUser loginUser) throws Exception {
        //"yyyy-MM-dd'T'HH:mm:ss.SSS"
        MallToken mallToken = new MallToken(loginUser.getUsername(),
                MD5Utils.encrypt(loginUser.getPassword()), "wx");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(mallToken);
        } catch (Exception e) {
            return BaseRespVo.fail("账号密码不对", 700);
        }

        WxUserLoginBO wxUserLoginBO = userService.userLoginInfo(loginUser.getUsername(), loginUser.getPassword());

        return BaseRespVo.ok(wxUserLoginBO);
    }

    @RequestMapping("logout")
    public WxLogoutMsg logout() {//登出
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
            return WxLogoutMsg.logout();
        }
        return WxLogoutMsg.fail();
    }

    @RequestMapping("regCaptcha")
    public BaseRespVo regCaptcha(@RequestBody Map<String, String> map) throws ClientException {
        Date now = new Date();
        expiredTime = new Date(now.getTime() + 5 * 60000);
        String s = new String(generateCheckPass());
        code = s;
        smsComponent.sendMsg(map.get("mobile"), code);
        return BaseRespVo.ok("验证码已发送");
    }

    //todo 手机验证，username 验证，验证码过期 ，未登录跳转
    @RequestMapping("register")
    public BaseRespVo register(@RequestBody RegisterUserBO registerUserBO) throws Exception {
        //{"errno":703,"errmsg":"验证码错误"}
        long time = new Date().getTime();
        long tt = expiredTime.getTime() - time;
        if (tt < 0) {
            return BaseRespVo.fail("验证码超时", 703);
        }
        if (!code.equals(registerUserBO.getCode())) {
            return BaseRespVo.fail("验证码错误", 703);
        }
        int i = userService.addUser(registerUserBO);

        if (i == 400) {
            return BaseRespVo.fail("用户名已注册", 700);
        }
        if (i == 401) {
            return BaseRespVo.fail("手机号已注册", 701);
        }

        MallToken mallToken = new MallToken(registerUserBO.getUsername(),
                MD5Utils.encrypt(registerUserBO.getPassword()), "wx");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(mallToken);
        } catch (Exception e) {
            return BaseRespVo.fail("账号密码不对", 700);
        }
        WxUserLoginBO wxUserLoginBO = userService.userLoginInfo(registerUserBO.getUsername(), registerUserBO.getPassword());
        return BaseRespVo.ok(wxUserLoginBO);
    }

    @RequestMapping("reset")
    public BaseRespVo reset(@RequestBody ResetBO resetBO) throws Exception {
        long time = new Date().getTime();
        long tt = expiredTime.getTime() - time;
        if (tt < 0) {
            return BaseRespVo.fail("验证码超时", 703);
        }
        if (!code.equals(resetBO.getCode())) {
            return BaseRespVo.fail("验证码错误", 703);
        }
        int i = userService.resetPsw(resetBO);
        if (i == 400) {
            return BaseRespVo.fail("手机号未注册", 703);
        }
        return BaseRespVo.ok();
    }

    //验证码随机数生产器
    public static char[] generateCheckPass() {
        String chars = "0123456789";
        char[] rands = new char[6];
        for (int i = 0; i < 6; i++) {
            int rand = (int) (Math.random() * 10);
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }
}
