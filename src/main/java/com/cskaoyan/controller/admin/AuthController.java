package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.vo.market.BaseRespVo;
import com.cskaoyan.bean.InfoData;
import com.cskaoyan.bean.LoginUser;
import com.cskaoyan.bean.vo.dashbord.AllKindsTotals;
import com.cskaoyan.service.admin.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Authenticate
 */
@RestController
@RequestMapping("admin")
public class AuthController {

    @Autowired
    AuthService authService;

    //@RequestMapping("admin/auth/login")
    @PostMapping("auth/login")
    public BaseRespVo login(@RequestBody LoginUser user) {
        //业务留给大家自己这部分
        //要学习完Shiro才能做这部分开发
        return BaseRespVo.ok("643fb2d4-80f8-48a6-92b2-ccddce036057");
    }

    @GetMapping("auth/info")//Get请求请求参数不可能是Json
    public BaseRespVo info(String token) {
        //查询用户信息的业务，大家自己来写 👉 需要自己来做
        InfoData infoData = new InfoData();
        infoData.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        infoData.setName("admin123");
        ArrayList<String> perms = new ArrayList<>();
        perms.add("*");
        infoData.setPerms(perms);
        ArrayList<String> roles = new ArrayList<>();
        roles.add("超级管理员");
        infoData.setRoles(roles);

        return BaseRespVo.ok(infoData);
    }

    @RequestMapping("dashboard")
    public BaseRespVo dashboard(){
        AllKindsTotals allKindsTotals = authService.queryTotals();
        return BaseRespVo.ok(allKindsTotals);
    }

}
