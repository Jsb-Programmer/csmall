package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.bo.auth.ChangePwdBO;
import com.cskaoyan.bean.vo.market.BaseRespVo;
import com.cskaoyan.bean.InfoData;
import com.cskaoyan.bean.LoginUser;
import com.cskaoyan.bean.vo.dashbord.AllKindsTotals;
import com.cskaoyan.realm.MallToken;
import com.cskaoyan.service.admin.AuthService;
import com.cskaoyan.service.admin.SystemService;
import com.cskaoyan.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Authenticate
 */
@RestController
@RequestMapping("admin")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    SystemService systemService;


    static int i = 0;
    static Date date;
    @PostMapping("auth/login")
    public BaseRespVo login(@RequestBody LoginUser user) throws Exception {
        MallToken admin = new MallToken(user.getUsername(),
                user.getPassword(), "admin");
        Subject subject = SecurityUtils.getSubject();
//        ------------------------------新增代码----------------------------------------------------
        if (i >= 2){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            Date now=sdf.parse(sdf.format(new Date()));
            long time = now.getTime()- date.getTime();
            if(time < 60*1000){
                String index = String.valueOf(60 - (time / 1000));
                return BaseRespVo.fail(605,"您的账号已被锁定，" + index + "秒后解锁");
            }else {
                i = 0;
                return BaseRespVo.fail(605,"您的账号已解除锁定，请重新登录");
            }
        }
//        ------------------------------------------------------------------------------------------
        try {
            subject.login(admin);
        } catch (Exception e) {

//        ------------------------------------新增代码-------------------------------------------------
            i++;
            String index = String.valueOf(3 - i);
            date = systemService.notLogin(user.getUsername());
            return BaseRespVo.fail(605,"用户帐号或密码不正确,你还可以输入" + index + "次");

        }

        //update login info for admin
        authService.updateAdminLoginInfo(user.getUsername());
        Session session = subject.getSession();
        return BaseRespVo.ok(session.getId());
//        ------------------------------------------------------------------------------------------

    }


    @GetMapping("auth/info")
    public BaseRespVo info() {

        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        InfoData admin = authService.queryAdminByName(principal);
        return BaseRespVo.ok(admin);
    }

    @RequestMapping("dashboard")
    public BaseRespVo dashboard() {

        AllKindsTotals allKindsTotals = authService.queryTotals();
        return BaseRespVo.ok(allKindsTotals);
    }

    @RequestMapping("auth/logout")
    public BaseRespVo logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseRespVo.ok();
    }

    @RequestMapping("profile/password")
    public BaseRespVo changePwd(@RequestBody ChangePwdBO changePwdBO) throws Exception {
        if (changePwdBO.getNewPassword().equals(" ")) {
            return BaseRespVo.fail(605, "新密码不能包含空格");
        }
        if (changePwdBO.getNewPassword().length() < 6) {
            return BaseRespVo.fail(605, "新密码少于6位");
        }
        if (!changePwdBO.getNewPassword().equals(changePwdBO.getNewPassword2())) {
            return BaseRespVo.fail(605, "两次新密码不同");
        }
        int code = authService.changeAdminPwd(changePwdBO.getOldPassword(),changePwdBO.getNewPassword());
        if (code == 400){
            return BaseRespVo.fail(605,"账号密码不对");
        }
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            subject.logout();//todo fe can be better
            return BaseRespVo.ok();
        }
        return BaseRespVo.ok();
    }
}
