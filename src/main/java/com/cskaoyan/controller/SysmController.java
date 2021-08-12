package com.cskaoyan.controller;


import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.bo.system.RoleOptions;
import com.cskaoyan.bean.pojo.Role;
import com.cskaoyan.bean.vo.system.AdminList;
import com.cskaoyan.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 系统管理-张明臣
 */
@RestController
@RequestMapping("admin")
public class SysmController {

    @Autowired
    SystemService systemService;

    /**
     * 模糊查询管理员的信息        role/options
     * @param username
     * @param param
     * @return
     */
    @RequestMapping("admin/list")
    public BaseRespVo adminList(String username, BaseParam param){
        BaseRespData baseRespData = systemService.adminList(username, param);
        return BaseRespVo.ok(baseRespData);
    }

    /**
     * 模拟登录
     * @return
     */
    @RequestMapping("auth/login")
    public String authogin(){
        return "{\"errno\":0,\"data\":\"a2b9b155-d85e-4c80-8ef9-f405fa6e2ee2\",\"errmsg\":\"成功\"}";
    }

    /**
     * 模糊查询log信息
     * @param name
     * @param param
     * @return
     */
    @RequestMapping("log/list")
    public BaseRespVo logList(String name, BaseParam param){
        BaseRespData baseRespData = systemService.logList(name, param);
        return BaseRespVo.ok(baseRespData,"成功");
    }

    /**
     * 模糊查询role
     * @param name
     * @param param
     * @return
     */
    @RequestMapping("role/list")
    public BaseRespVo roleList(String name, BaseParam param){
        BaseRespData baseRespData = systemService.roleList(name, param);
        return BaseRespVo.ok(baseRespData,"成功");
    }

    /**
     * role创建
     * @param map
     * @return
     */
    @RequestMapping("role/create")
    public BaseRespVo roleCreate(@RequestBody Map<String,String> map){
        Role role = systemService.roleCreate(map.get("name"), map.get("desc"));
        return BaseRespVo.ok(role,"成功");
    }

    @RequestMapping("role/options")
    public BaseRespVo roleOptions(){
        List<RoleOptions> list = systemService.roleOptions();
        return BaseRespVo.ok(list);
    }




    /**
     * 对于角色的删除目前 没写完
     * @param role
     * @return
     */
    @RequestMapping("role/delete")
    public BaseRespVo roleDelete(@RequestBody Role role){
        systemService.roleDelete(role.getId());
        return BaseRespVo.ok(role,"成功");
    }

    /**
     * role信息修改
     * @param role
     * @return
     */
    @RequestMapping("role/update")
    public BaseRespVo roleUpdate(@RequestBody Role role){
        systemService.roleUpdate(role);
        return BaseRespVo.ok("成功");
    }


    /**
     * 通过key 和 name 模糊查询storage           storage/create
     * @param name
     * @param key
     * @param param
     * @return
     */
    @RequestMapping("storage/list")
    public BaseRespVo storageList(String name, String key,BaseParam param){
        BaseRespData baseRespData = systemService.storageList(name,key, param);
        return BaseRespVo.ok(baseRespData,"成功");
    }


//                          该接口 吴寒 已写
//
//    /**
//     * 上传管理员头像
//     */
//    @RequestMapping("storage/create")
//    public BaseRespVo storageCreate(MultipartFile multipartFile){
//        systemService.storageCreate(multipartFile);
//        return BaseRespVo.ok(baseRespData,"成功");
//    }


}
