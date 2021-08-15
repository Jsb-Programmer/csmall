package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.vo.market.BaseRespVo;
import com.cskaoyan.bean.InfoData;
import com.cskaoyan.bean.LoginUser;
import com.cskaoyan.bean.vo.dashbord.AllKindsTotals;
import com.cskaoyan.realm.MallToken;
import com.cskaoyan.service.admin.AuthService;
import com.cskaoyan.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Authenticate
 */
//@RestController
@RequestMapping("admin")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("auth/login")
    public BaseRespVo login(@RequestBody LoginUser user) throws Exception {
        MallToken admin = new MallToken(user.getUsername(),
                MD5Utils.encrypt(user.getPassword()), "admin");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(admin);
        } catch (Exception e) {
            return BaseRespVo.fail(605,"用户帐号或密码不正确");
        }
        Session session = subject.getSession();
        return BaseRespVo.ok(session.getId());
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

}

/*

        InfoData infoData = new InfoData();
        infoData.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        infoData.setName("admin123");
        ArrayList<String> perms = new ArrayList<>();
        perms.add("*");
        infoData.setPerms(perms);
        ArrayList<String> roles = new ArrayList<>();
        roles.add("超级管理员");
        infoData.setRoles(roles);
 */