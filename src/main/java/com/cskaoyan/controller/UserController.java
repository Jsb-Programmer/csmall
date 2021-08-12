package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.vo.market.BaseRespVo;
import com.cskaoyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("list")
    //public BaseRespVo list(Integer page, Integer limit, String username, String mobile, String sort, String order) {
    public BaseRespVo list(String username, String mobile, BaseParam param) {
        BaseRespData data = userService.query(username,mobile,param);
        return BaseRespVo.ok(data);
    }
}
