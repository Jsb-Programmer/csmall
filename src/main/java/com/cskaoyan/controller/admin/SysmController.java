package com.cskaoyan.controller.admin;


import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.bo.system.RoleOptions;
import com.cskaoyan.bean.bo.system.RolePermissionsUpdate;
import com.cskaoyan.bean.pojo.Admin;
import com.cskaoyan.bean.pojo.Role;
import com.cskaoyan.bean.pojo.Storage;
import com.cskaoyan.bean.vo.system.RolePermission;
import com.cskaoyan.service.admin.SystemService;
import com.google.gson.Gson;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 模糊查询管理员的信息
     * @param username
     * @param param
     * @return
     */
    @RequiresPermissions("admin:admin:list")
    @RequestMapping("admin/list")
    public BaseRespVo adminList(String username, BaseParam param){
        BaseRespData baseRespData = systemService.adminList(username, param);
        return BaseRespVo.ok(baseRespData);
    }


    /**
     * 管理员创建             admin/update
     * @param admin
     * @return
     *
     *     {"errno":601,"errmsg":"管理员名称不符合规定"}
     */
    @RequiresPermissions("admin:admin:create")
    @RequestMapping("admin/create")
    public BaseRespVo adminCreate(@RequestBody Admin admin){
        Object o = systemService.adminCreate(admin);
        if (o instanceof Admin) {
            return BaseRespVo.ok(((Admin) o));
        }
        if (new Integer(1).equals(o)){
            BaseRespVo<Object> respVo = new BaseRespVo<>();
            respVo.setErrno(601);
            respVo.setErrmsg("管理员名称不符合规定");
            return respVo;
        }
        if (new Integer(0).equals(o)){
            BaseRespVo<Object> respVo = new BaseRespVo<>();
            respVo.setErrno(601);
            respVo.setErrmsg("密码必须长于6位");
            return respVo;
        }
        return null;
    }

    /**
     * admin信息修改
     * @param admin
     * @return
     */
    @RequiresPermissions("admin:admin:update")
    @RequestMapping("admin/update")
    public BaseRespVo adminUpdate(@RequestBody Admin admin){
//        Admin adminCreate = systemService.adminUpdate(admin);
//        return BaseRespVo.ok(adminCreate);
        Object o = systemService.adminUpdate(admin);
        if (o instanceof Admin) {
            return BaseRespVo.ok(((Admin) o));
        }
        if (new Integer(1).equals(o)){
            BaseRespVo<Object> respVo = new BaseRespVo<>();
            respVo.setErrno(601);
            respVo.setErrmsg("管理员名称不符合规定");
            return respVo;
        }
        if (new Integer(0).equals(o)){
            BaseRespVo<Object> respVo = new BaseRespVo<>();
            respVo.setErrno(601);
            respVo.setErrmsg("密码必须长于6位");
            return respVo;
        }
        return null;
    }

    /**
     *
     * @param admin
     * @return
     */
    @RequiresPermissions("admin:admin:delete")
    @RequestMapping("admin/delete")
    public BaseRespVo adminDelete(@RequestBody Admin admin){
        Integer affectedRows = systemService.adminDelete(admin);
        return BaseRespVo.ok();
    }

    /**
     * 模拟登录
     * @return
     */
//    @RequestMapping("auth/login")
//    public String authogin(){
//        return "{\"errno\":0,\"data\":\"a2b9b155-d85e-4c80-8ef9-f405fa6e2ee2\",\"errmsg\":\"成功\"}";
//    }

    /**
     * 模糊查询log信息
     * @param name
     * @param param
     * @return
     */
    @RequiresPermissions("admin:log:list")
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
    @RequiresPermissions("admin:role:list")
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
    @RequiresPermissions("admin:role:create")
    @RequestMapping("role/create")
    public BaseRespVo roleCreate(@RequestBody Map<String,String> map){
        Role role = systemService.roleCreate(map.get("name"), map.get("desc"));
        return BaseRespVo.ok(role,"成功");
    }


    /**
     * 角色的全部
     * @return
     */
    @RequestMapping("role/options")
    public BaseRespVo roleOptions(){
        List<RoleOptions> list = systemService.roleOptions();
        return BaseRespVo.ok(list);
    }




    /**
     * 对于角色的删除目前
     * @param role
     * @return
     */
    @RequiresPermissions("admin:role:delete")
    @RequestMapping("role/delete")
    public BaseRespVo roleDelete(@RequestBody Role role){
        String s = systemService.roleDelete(role);
        if (s == null)
            return BaseRespVo.ok(role,"成功");
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
        objectBaseRespVo.setErrno(642);
        objectBaseRespVo.setErrmsg(s);
        return objectBaseRespVo;
    }

    /**
     * role信息修改
     * @param role
     * @return
     */
    @RequiresPermissions("admin:role:update")
    @RequestMapping("role/update")
    public BaseRespVo roleUpdate(@RequestBody Role role){
        systemService.roleUpdate(role);
        return BaseRespVo.ok("成功");
    }


    /**
     * 通过key 和 name 模糊查询storage
     * @param name
     * @param key
     * @param param
     * @return
     */
    @RequiresPermissions("admin:storage:list")
    @RequestMapping("storage/list")
    public BaseRespVo storageList(String name, String key,BaseParam param){
        BaseRespData baseRespData = systemService.storageList(name,key, param);
        return BaseRespVo.ok(baseRespData,"成功");
    }

    /**
     * 修改文件名   storage/delete
     * @param storage
     * @return
     */
    @RequiresPermissions("admin:storage:update")
    @RequestMapping("storage/update")
    public BaseRespVo storageUpdate(@RequestBody Storage storage){
        int i = systemService.storageUpdate(storage);
        return BaseRespVo.ok();
    }

    @RequiresPermissions("admin:storage:delete")
    @RequestMapping("storage/delete")
    public BaseRespVo storageDelete(@RequestBody Storage storage){
        int i = systemService.storageDelete(storage);
        return BaseRespVo.ok();
    }

    /**
     * 全部权限的回显
     * @param
     * @return
     */
    @GetMapping("role/permissions")
    public String rolePermissions(Integer roleId){
        if (roleId == 1){
            return "{\"errno\":0,\"data\":{\"systemPermissions\":[{\"id\":\"系统管理\",\"label\":\"系统管理\",\"children\":[{\"id\":\"管理员管理\",\"label\":\"管理员管理\",\"children\":[{\"id\":\"admin:admin:update\",\"label\":\"编辑\",\"api\":\"POST /admin/admin/update\"},{\"id\":\"admin:admin:list\",\"label\":\"查询\",\"api\":\"GET /admin/admin/list\"},{\"id\":\"admin:admin:read\",\"label\":\"详情\",\"api\":\"GET /admin/admin/read\"},{\"id\":\"admin:admin:delete\",\"label\":\"删除\",\"api\":\"POST /admin/admin/delete\"},{\"id\":\"admin:admin:create\",\"label\":\"添加\",\"api\":\"POST /admin/admin/create\"}]},{\"id\":\"角色管理\",\"label\":\"角色管理\",\"children\":[{\"id\":\"admin:role:permission:update\",\"label\":\"权限变更\",\"api\":\"POST /admin/role/permissions\"},{\"id\":\"admin:role:update\",\"label\":\"角色编辑\",\"api\":\"POST /admin/role/update\"},{\"id\":\"admin:role:list\",\"label\":\"角色查询\",\"api\":\"GET /admin/role/list\"},{\"id\":\"admin:role:read\",\"label\":\"角色详情\",\"api\":\"GET /admin/role/read\"},{\"id\":\"admin:role:delete\",\"label\":\"角色删除\",\"api\":\"POST /admin/role/delete\"},{\"id\":\"admin:role:permission:get\",\"label\":\"权限详情\",\"api\":\"GET /admin/role/permissions\"},{\"id\":\"admin:role:create\",\"label\":\"角色添加\",\"api\":\"POST /admin/role/create\"}]},{\"id\":\"对象存储\",\"label\":\"对象存储\",\"children\":[{\"id\":\"admin:storage:update\",\"label\":\"编辑\",\"api\":\"POST /admin/storage/update\"},{\"id\":\"admin:storage:list\",\"label\":\"查询\",\"api\":\"GET /admin/storage/list\"},{\"id\":\"admin:storage:read\",\"label\":\"详情\",\"api\":\"POST /admin/storage/read\"},{\"id\":\"admin:storage:delete\",\"label\":\"删除\",\"api\":\"POST /admin/storage/delete\"},{\"id\":\"admin:storage:create\",\"label\":\"上传\",\"api\":\"POST /admin/storage/create\"}]},{\"id\":\"操作日志\",\"label\":\"操作日志\",\"children\":[{\"id\":\"admin:log:list\",\"label\":\"查询\",\"api\":\"GET /admin/log/list\"}]}]},{\"id\":\"商场管理\",\"label\":\"商场管理\",\"children\":[{\"id\":\"品牌管理\",\"label\":\"品牌管理\",\"children\":[{\"id\":\"admin:brand:update\",\"label\":\"编辑\",\"api\":\"POST /admin/brand/update\"},{\"id\":\"admin:brand:list\",\"label\":\"查询\",\"api\":\"GET /admin/brand/list\"},{\"id\":\"admin:brand:read\",\"label\":\"详情\",\"api\":\"GET /admin/brand/read\"},{\"id\":\"admin:brand:delete\",\"label\":\"删除\",\"api\":\"POST /admin/brand/delete\"},{\"id\":\"admin:brand:create\",\"label\":\"添加\",\"api\":\"POST /admin/brand/create\"}]},{\"id\":\"通用问题\",\"label\":\"通用问题\",\"children\":[{\"id\":\"admin:issue:update\",\"label\":\"编辑\",\"api\":\"POST /admin/issue/update\"},{\"id\":\"admin:issue:list\",\"label\":\"查询\",\"api\":\"GET /admin/issue/list\"},{\"id\":\"admin:issue:delete\",\"label\":\"删除\",\"api\":\"POST /admin/issue/delete\"},{\"id\":\"admin:issue:create\",\"label\":\"添加\",\"api\":\"POST /admin/issue/create\"}]},{\"id\":\"订单管理\",\"label\":\"订单管理\",\"children\":[{\"id\":\"admin:order:read\",\"label\":\"详情\",\"api\":\"GET /admin/order/detail\"},{\"id\":\"admin:order:ship\",\"label\":\"订单发货\",\"api\":\"POST /admin/order/ship\"},{\"id\":\"admin:order:reply\",\"label\":\"订单商品回复\",\"api\":\"POST /admin/order/reply\"},{\"id\":\"admin:order:refund\",\"label\":\"订单退款\",\"api\":\"POST /admin/order/refund\"},{\"id\":\"admin:order:list\",\"label\":\"查询\",\"api\":\"GET /admin/order/list\"}]},{\"id\":\"类目管理\",\"label\":\"类目管理\",\"children\":[{\"id\":\"admin:category:update\",\"label\":\"编辑\",\"api\":\"POST /admin/category/update\"},{\"id\":\"admin:category:list\",\"label\":\"查询\",\"api\":\"GET /admin/category/list\"},{\"id\":\"admin:category:read\",\"label\":\"详情\",\"api\":\"GET /admin/category/read\"},{\"id\":\"admin:category:delete\",\"label\":\"删除\",\"api\":\"POST /admin/category/delete\"},{\"id\":\"admin:category:create\",\"label\":\"添加\",\"api\":\"POST /admin/category/create\"}]},{\"id\":\"关键词\",\"label\":\"关键词\",\"children\":[{\"id\":\"admin:keyword:update\",\"label\":\"编辑\",\"api\":\"POST /admin/keyword/update\"},{\"id\":\"admin:keyword:list\",\"label\":\"查询\",\"api\":\"GET /admin/keyword/list\"},{\"id\":\"admin:keyword:read\",\"label\":\"详情\",\"api\":\"GET /admin/keyword/read\"},{\"id\":\"admin:keyword:delete\",\"label\":\"删除\",\"api\":\"POST /admin/keyword/delete\"},{\"id\":\"admin:keyword:create\",\"label\":\"添加\",\"api\":\"POST /admin/keyword/create\"}]}]},{\"id\":\"用户管理\",\"label\":\"用户管理\",\"children\":[{\"id\":\"用户收藏\",\"label\":\"用户收藏\",\"children\":[{\"id\":\"admin:collect:list\",\"label\":\"查询\",\"api\":\"GET /admin/collect/list\"}]},{\"id\":\"用户足迹\",\"label\":\"用户足迹\",\"children\":[{\"id\":\"admin:footprint:list\",\"label\":\"查询\",\"api\":\"GET /admin/footprint/list\"}]},{\"id\":\"意见反馈\",\"label\":\"意见反馈\",\"children\":[{\"id\":\"admin:feedback:list\",\"label\":\"查询\",\"api\":\"GET /admin/feedback/list\"}]},{\"id\":\"搜索历史\",\"label\":\"搜索历史\",\"children\":[{\"id\":\"admin:history:list\",\"label\":\"查询\",\"api\":\"GET /admin/history/list\"}]},{\"id\":\"会员管理\",\"label\":\"会员管理\",\"children\":[{\"id\":\"admin:user:list\",\"label\":\"查询\",\"api\":\"GET /admin/user/list\"}]},{\"id\":\"收货地址\",\"label\":\"收货地址\",\"children\":[{\"id\":\"admin:address:list\",\"label\":\"查询\",\"api\":\"GET /admin/address/list\"}]}]},{\"id\":\"配置管理\",\"label\":\"配置管理\",\"children\":[{\"id\":\"小程序配置\",\"label\":\"小程序配置\",\"children\":[{\"id\":\"admin:config:wx:list\",\"label\":\"详情\",\"api\":\"GET /admin/config/wx\"},{\"id\":\"admin:config:wx:updateConfigs\",\"label\":\"编辑\",\"api\":\"POST /admin/config/wx\"}]},{\"id\":\"运费配置\",\"label\":\"运费配置\",\"children\":[{\"id\":\"admin:config:express:list\",\"label\":\"详情\",\"api\":\"GET /admin/config/express\"},{\"id\":\"admin:config:express:updateConfigs\",\"label\":\"编辑\",\"api\":\"POST /admin/config/express\"}]},{\"id\":\"商场配置\",\"label\":\"商场配置\",\"children\":[{\"id\":\"admin:config:mall:list\",\"label\":\"详情\",\"api\":\"GET /admin/config/mall\"},{\"id\":\"admin:config:mall:updateConfigs\",\"label\":\"编辑\",\"api\":\"POST /admin/config/mall\"}]},{\"id\":\"订单配置\",\"label\":\"订单配置\",\"children\":[{\"id\":\"admin:config:order:list\",\"label\":\"详情\",\"api\":\"GET /admin/config/order\"},{\"id\":\"admin:config:order:updateConfigs\",\"label\":\"编辑\",\"api\":\"POST /admin/config/order\"}]}]},{\"id\":\"推广管理\",\"label\":\"推广管理\",\"children\":[{\"id\":\"广告管理\",\"label\":\"广告管理\",\"children\":[{\"id\":\"admin:ad:update\",\"label\":\"编辑\",\"api\":\"POST /admin/ad/update\"},{\"id\":\"admin:ad:list\",\"label\":\"查询\",\"api\":\"GET /admin/ad/list\"},{\"id\":\"admin:ad:read\",\"label\":\"详情\",\"api\":\"GET /admin/ad/read\"},{\"id\":\"admin:ad:delete\",\"label\":\"删除\",\"api\":\"POST /admin/ad/delete\"},{\"id\":\"admin:ad:create\",\"label\":\"添加\",\"api\":\"POST /admin/ad/create\"}]},{\"id\":\"专题管理\",\"label\":\"专题管理\",\"children\":[{\"id\":\"admin:topic:update\",\"label\":\"编辑\",\"api\":\"POST /admin/topic/update\"},{\"id\":\"admin:topic:list\",\"label\":\"查询\",\"api\":\"GET /admin/topic/list\"},{\"id\":\"admin:topic:read\",\"label\":\"详情\",\"api\":\"GET /admin/topic/read\"},{\"id\":\"admin:topic:delete\",\"label\":\"删除\",\"api\":\"POST /admin/topic/delete\"},{\"id\":\"admin:topic:create\",\"label\":\"添加\",\"api\":\"POST /admin/topic/create\"}]},{\"id\":\"优惠券管理\",\"label\":\"优惠券管理\",\"children\":[{\"id\":\"admin:coupon:listuser\",\"label\":\"查询用户\",\"api\":\"GET /admin/coupon/listuser\"},{\"id\":\"admin:coupon:update\",\"label\":\"编辑\",\"api\":\"POST /admin/coupon/update\"},{\"id\":\"admin:coupon:list\",\"label\":\"查询\",\"api\":\"GET /admin/coupon/list\"},{\"id\":\"admin:coupon:read\",\"label\":\"详情\",\"api\":\"GET /admin/coupon/read\"},{\"id\":\"admin:coupon:delete\",\"label\":\"删除\",\"api\":\"POST /admin/coupon/delete\"},{\"id\":\"admin:coupon:create\",\"label\":\"添加\",\"api\":\"POST /admin/coupon/create\"}]},{\"id\":\"团购管理\",\"label\":\"团购管理\",\"children\":[{\"id\":\"admin:groupon:read\",\"label\":\"详情\",\"api\":\"GET /admin/groupon/listRecord\"},{\"id\":\"admin:groupon:update\",\"label\":\"编辑\",\"api\":\"POST /admin/groupon/update\"},{\"id\":\"admin:groupon:list\",\"label\":\"查询\",\"api\":\"GET /admin/groupon/list\"},{\"id\":\"admin:groupon:delete\",\"label\":\"删除\",\"api\":\"POST /admin/groupon/delete\"},{\"id\":\"admin:groupon:create\",\"label\":\"添加\",\"api\":\"POST /admin/groupon/create\"}]}]},{\"id\":\"商品管理\",\"label\":\"商品管理\",\"children\":[{\"id\":\"商品管理\",\"label\":\"商品管理\",\"children\":[{\"id\":\"admin:goods:read\",\"label\":\"详情\",\"api\":\"GET /admin/goods/detail\"},{\"id\":\"admin:goods:update\",\"label\":\"编辑\",\"api\":\"POST /admin/goods/update\"},{\"id\":\"admin:goods:list\",\"label\":\"查询\",\"api\":\"GET /admin/goods/list\"},{\"id\":\"admin:goods:delete\",\"label\":\"删除\",\"api\":\"POST /admin/goods/delete\"},{\"id\":\"admin:goods:create\",\"label\":\"上架\",\"api\":\"POST /admin/goods/create\"}]},{\"id\":\"评论管理\",\"label\":\"评论管理\",\"children\":[{\"id\":\"admin:comment:list\",\"label\":\"查询\",\"api\":\"GET /admin/comment/list\"},{\"id\":\"admin:comment:delete\",\"label\":\"删除\",\"api\":\"POST /admin/comment/delete\"}]}]},{\"id\":\"其他\",\"label\":\"其他\",\"children\":[{\"id\":\"权限测试\",\"label\":\"权限测试\",\"children\":[{\"id\":\"index:permission:write\",\"label\":\"权限写\",\"api\":\"POST /admin/index/write\"},{\"id\":\"index:permission:read\",\"label\":\"权限读\",\"api\":\"GET /admin/index/read\"}]}]},{\"id\":\"统计管理\",\"label\":\"统计管理\",\"children\":[{\"id\":\"用户统计\",\"label\":\"用户统计\",\"children\":[{\"id\":\"admin:stat:user\",\"label\":\"查询\",\"api\":\"GET /admin/stat/user\"}]},{\"id\":\"订单统计\",\"label\":\"订单统计\",\"children\":[{\"id\":\"admin:stat:order\",\"label\":\"查询\",\"api\":\"GET /admin/stat/order\"}]},{\"id\":\"商品统计\",\"label\":\"商品统计\",\"children\":[{\"id\":\"admin:stat:goods\",\"label\":\"查询\",\"api\":\"GET /admin/stat/goods\"}]}]}],\"assignedPermissions\":[\"admin:order:list\",\"admin:admin:update\",\"admin:topic:read\",\"admin:coupon:delete\",\"admin:admin:delete\",\"admin:goods:update\",\"admin:user:list\",\"admin:role:permission:get\",\"admin:brand:update\",\"admin:category:create\",\"admin:ad:create\",\"admin:coupon:list\",\"admin:stat:order\",\"admin:config:wx:updateConfigs\",\"admin:topic:list\",\"admin:order:refund\",\"admin:order:read\",\"admin:topic:delete\",\"admin:brand:list\",\"admin:brand:delete\",\"admin:coupon:update\",\"admin:brand:read\",\"admin:config:wx:list\",\"admin:collect:list\",\"admin:storage:list\",\"admin:coupon:listuser\",\"admin:groupon:read\",\"admin:admin:read\",\"admin:order:ship\",\"admin:storage:read\",\"admin:keyword:update\",\"admin:comment:delete\",\"admin:groupon:create\",\"admin:comment:list\",\"admin:keyword:list\",\"admin:keyword:create\",\"admin:admin:list\",\"admin:category:delete\",\"admin:history:list\",\"admin:role:delete\",\"admin:storage:delete\",\"admin:order:reply\",\"admin:keyword:read\",\"admin:ad:delete\",\"admin:goods:delete\",\"admin:issue:update\",\"admin:topic:create\",\"admin:address:list\",\"admin:category:read\",\"admin:category:update\",\"admin:config:express:updateConfigs\",\"admin:storage:create\",\"admin:brand:create\",\"admin:config:express:list\",\"admin:issue:delete\",\"admin:goods:create\",\"admin:ad:list\",\"admin:role:permission:update\",\"admin:groupon:list\",\"admin:admin:create\",\"admin:groupon:update\",\"admin:footprint:list\",\"index:permission:write\",\"admin:ad:read\",\"admin:groupon:delete\",\"admin:config:order:list\",\"index:permission:read\",\"admin:keyword:delete\",\"admin:role:create\",\"admin:config:order:updateConfigs\",\"admin:issue:list\",\"admin:log:list\",\"admin:topic:update\",\"admin:config:mall:list\",\"admin:category:list\",\"admin:stat:goods\",\"admin:issue:create\",\"admin:config:mall:updateConfigs\",\"admin:role:update\",\"admin:stat:user\",\"admin:goods:list\",\"admin:coupon:read\",\"admin:coupon:create\",\"admin:ad:update\",\"admin:role:list\",\"admin:storage:update\",\"admin:role:read\",\"admin:feedback:list\",\"admin:goods:read\"]},\"errmsg\":\"成功\"}";
        }
        RolePermission rolePermission = systemService.rolePermissions(roleId);
        BaseRespVo ok = BaseRespVo.ok(rolePermission);
        Gson gson = new Gson();
        String s = gson.toJson(ok);
        s = s.replace("\"****\"", "[{\"id\":\"系统管理\",\"label\":\"系统管理\",\"children\":[{\"id\":\"管理员管理\",\"label\":\"管理员管理\",\"children\":[{\"id\":\"admin:admin:update\",\"label\":\"编辑\",\"api\":\"POST /admin/admin/update\"},{\"id\":\"admin:admin:list\",\"label\":\"查询\",\"api\":\"GET /admin/admin/list\"},{\"id\":\"admin:admin:read\",\"label\":\"详情\",\"api\":\"GET /admin/admin/read\"},{\"id\":\"admin:admin:delete\",\"label\":\"删除\",\"api\":\"POST /admin/admin/delete\"},{\"id\":\"admin:admin:create\",\"label\":\"添加\",\"api\":\"POST /admin/admin/create\"}]},{\"id\":\"角色管理\",\"label\":\"角色管理\",\"children\":[{\"id\":\"admin:role:permission:update\",\"label\":\"权限变更\",\"api\":\"POST /admin/role/permissions\"},{\"id\":\"admin:role:update\",\"label\":\"角色编辑\",\"api\":\"POST /admin/role/update\"},{\"id\":\"admin:role:list\",\"label\":\"角色查询\",\"api\":\"GET /admin/role/list\"},{\"id\":\"admin:role:read\",\"label\":\"角色详情\",\"api\":\"GET /admin/role/read\"},{\"id\":\"admin:role:delete\",\"label\":\"角色删除\",\"api\":\"POST /admin/role/delete\"},{\"id\":\"admin:role:permission:get\",\"label\":\"权限详情\",\"api\":\"GET /admin/role/permissions\"},{\"id\":\"admin:role:create\",\"label\":\"角色添加\",\"api\":\"POST /admin/role/create\"}]},{\"id\":\"对象存储\",\"label\":\"对象存储\",\"children\":[{\"id\":\"admin:storage:update\",\"label\":\"编辑\",\"api\":\"POST /admin/storage/update\"},{\"id\":\"admin:storage:list\",\"label\":\"查询\",\"api\":\"GET /admin/storage/list\"},{\"id\":\"admin:storage:read\",\"label\":\"详情\",\"api\":\"POST /admin/storage/read\"},{\"id\":\"admin:storage:delete\",\"label\":\"删除\",\"api\":\"POST /admin/storage/delete\"},{\"id\":\"admin:storage:create\",\"label\":\"上传\",\"api\":\"POST /admin/storage/create\"}]},{\"id\":\"操作日志\",\"label\":\"操作日志\",\"children\":[{\"id\":\"admin:log:list\",\"label\":\"查询\",\"api\":\"GET /admin/log/list\"}]}]},{\"id\":\"商场管理\",\"label\":\"商场管理\",\"children\":[{\"id\":\"品牌管理\",\"label\":\"品牌管理\",\"children\":[{\"id\":\"admin:brand:update\",\"label\":\"编辑\",\"api\":\"POST /admin/brand/update\"},{\"id\":\"admin:brand:list\",\"label\":\"查询\",\"api\":\"GET /admin/brand/list\"},{\"id\":\"admin:brand:read\",\"label\":\"详情\",\"api\":\"GET /admin/brand/read\"},{\"id\":\"admin:brand:delete\",\"label\":\"删除\",\"api\":\"POST /admin/brand/delete\"},{\"id\":\"admin:brand:create\",\"label\":\"添加\",\"api\":\"POST /admin/brand/create\"}]},{\"id\":\"通用问题\",\"label\":\"通用问题\",\"children\":[{\"id\":\"admin:issue:update\",\"label\":\"编辑\",\"api\":\"POST /admin/issue/update\"},{\"id\":\"admin:issue:list\",\"label\":\"查询\",\"api\":\"GET /admin/issue/list\"},{\"id\":\"admin:issue:delete\",\"label\":\"删除\",\"api\":\"POST /admin/issue/delete\"},{\"id\":\"admin:issue:create\",\"label\":\"添加\",\"api\":\"POST /admin/issue/create\"}]},{\"id\":\"订单管理\",\"label\":\"订单管理\",\"children\":[{\"id\":\"admin:order:read\",\"label\":\"详情\",\"api\":\"GET /admin/order/detail\"},{\"id\":\"admin:order:ship\",\"label\":\"订单发货\",\"api\":\"POST /admin/order/ship\"},{\"id\":\"admin:order:reply\",\"label\":\"订单商品回复\",\"api\":\"POST /admin/order/reply\"},{\"id\":\"admin:order:refund\",\"label\":\"订单退款\",\"api\":\"POST /admin/order/refund\"},{\"id\":\"admin:order:list\",\"label\":\"查询\",\"api\":\"GET /admin/order/list\"}]},{\"id\":\"类目管理\",\"label\":\"类目管理\",\"children\":[{\"id\":\"admin:category:update\",\"label\":\"编辑\",\"api\":\"POST /admin/category/update\"},{\"id\":\"admin:category:list\",\"label\":\"查询\",\"api\":\"GET /admin/category/list\"},{\"id\":\"admin:category:read\",\"label\":\"详情\",\"api\":\"GET /admin/category/read\"},{\"id\":\"admin:category:delete\",\"label\":\"删除\",\"api\":\"POST /admin/category/delete\"},{\"id\":\"admin:category:create\",\"label\":\"添加\",\"api\":\"POST /admin/category/create\"}]},{\"id\":\"关键词\",\"label\":\"关键词\",\"children\":[{\"id\":\"admin:keyword:update\",\"label\":\"编辑\",\"api\":\"POST /admin/keyword/update\"},{\"id\":\"admin:keyword:list\",\"label\":\"查询\",\"api\":\"GET /admin/keyword/list\"},{\"id\":\"admin:keyword:read\",\"label\":\"详情\",\"api\":\"GET /admin/keyword/read\"},{\"id\":\"admin:keyword:delete\",\"label\":\"删除\",\"api\":\"POST /admin/keyword/delete\"},{\"id\":\"admin:keyword:create\",\"label\":\"添加\",\"api\":\"POST /admin/keyword/create\"}]}]},{\"id\":\"用户管理\",\"label\":\"用户管理\",\"children\":[{\"id\":\"用户收藏\",\"label\":\"用户收藏\",\"children\":[{\"id\":\"admin:collect:list\",\"label\":\"查询\",\"api\":\"GET /admin/collect/list\"}]},{\"id\":\"用户足迹\",\"label\":\"用户足迹\",\"children\":[{\"id\":\"admin:footprint:list\",\"label\":\"查询\",\"api\":\"GET /admin/footprint/list\"}]},{\"id\":\"意见反馈\",\"label\":\"意见反馈\",\"children\":[{\"id\":\"admin:feedback:list\",\"label\":\"查询\",\"api\":\"GET /admin/feedback/list\"}]},{\"id\":\"搜索历史\",\"label\":\"搜索历史\",\"children\":[{\"id\":\"admin:history:list\",\"label\":\"查询\",\"api\":\"GET /admin/history/list\"}]},{\"id\":\"会员管理\",\"label\":\"会员管理\",\"children\":[{\"id\":\"admin:user:list\",\"label\":\"查询\",\"api\":\"GET /admin/user/list\"}]},{\"id\":\"收货地址\",\"label\":\"收货地址\",\"children\":[{\"id\":\"admin:address:list\",\"label\":\"查询\",\"api\":\"GET /admin/address/list\"}]}]},{\"id\":\"配置管理\",\"label\":\"配置管理\",\"children\":[{\"id\":\"小程序配置\",\"label\":\"小程序配置\",\"children\":[{\"id\":\"admin:config:wx:list\",\"label\":\"详情\",\"api\":\"GET /admin/config/wx\"},{\"id\":\"admin:config:wx:updateConfigs\",\"label\":\"编辑\",\"api\":\"POST /admin/config/wx\"}]},{\"id\":\"运费配置\",\"label\":\"运费配置\",\"children\":[{\"id\":\"admin:config:express:list\",\"label\":\"详情\",\"api\":\"GET /admin/config/express\"},{\"id\":\"admin:config:express:updateConfigs\",\"label\":\"编辑\",\"api\":\"POST /admin/config/express\"}]},{\"id\":\"商场配置\",\"label\":\"商场配置\",\"children\":[{\"id\":\"admin:config:mall:list\",\"label\":\"详情\",\"api\":\"GET /admin/config/mall\"},{\"id\":\"admin:config:mall:updateConfigs\",\"label\":\"编辑\",\"api\":\"POST /admin/config/mall\"}]},{\"id\":\"订单配置\",\"label\":\"订单配置\",\"children\":[{\"id\":\"admin:config:order:list\",\"label\":\"详情\",\"api\":\"GET /admin/config/order\"},{\"id\":\"admin:config:order:updateConfigs\",\"label\":\"编辑\",\"api\":\"POST /admin/config/order\"}]}]},{\"id\":\"推广管理\",\"label\":\"推广管理\",\"children\":[{\"id\":\"广告管理\",\"label\":\"广告管理\",\"children\":[{\"id\":\"admin:ad:update\",\"label\":\"编辑\",\"api\":\"POST /admin/ad/update\"},{\"id\":\"admin:ad:list\",\"label\":\"查询\",\"api\":\"GET /admin/ad/list\"},{\"id\":\"admin:ad:read\",\"label\":\"详情\",\"api\":\"GET /admin/ad/read\"},{\"id\":\"admin:ad:delete\",\"label\":\"删除\",\"api\":\"POST /admin/ad/delete\"},{\"id\":\"admin:ad:create\",\"label\":\"添加\",\"api\":\"POST /admin/ad/create\"}]},{\"id\":\"专题管理\",\"label\":\"专题管理\",\"children\":[{\"id\":\"admin:topic:update\",\"label\":\"编辑\",\"api\":\"POST /admin/topic/update\"},{\"id\":\"admin:topic:list\",\"label\":\"查询\",\"api\":\"GET /admin/topic/list\"},{\"id\":\"admin:topic:read\",\"label\":\"详情\",\"api\":\"GET /admin/topic/read\"},{\"id\":\"admin:topic:delete\",\"label\":\"删除\",\"api\":\"POST /admin/topic/delete\"},{\"id\":\"admin:topic:create\",\"label\":\"添加\",\"api\":\"POST /admin/topic/create\"}]},{\"id\":\"优惠券管理\",\"label\":\"优惠券管理\",\"children\":[{\"id\":\"admin:coupon:listuser\",\"label\":\"查询用户\",\"api\":\"GET /admin/coupon/listuser\"},{\"id\":\"admin:coupon:update\",\"label\":\"编辑\",\"api\":\"POST /admin/coupon/update\"},{\"id\":\"admin:coupon:list\",\"label\":\"查询\",\"api\":\"GET /admin/coupon/list\"},{\"id\":\"admin:coupon:read\",\"label\":\"详情\",\"api\":\"GET /admin/coupon/read\"},{\"id\":\"admin:coupon:delete\",\"label\":\"删除\",\"api\":\"POST /admin/coupon/delete\"},{\"id\":\"admin:coupon:create\",\"label\":\"添加\",\"api\":\"POST /admin/coupon/create\"}]},{\"id\":\"团购管理\",\"label\":\"团购管理\",\"children\":[{\"id\":\"admin:groupon:read\",\"label\":\"详情\",\"api\":\"GET /admin/groupon/listRecord\"},{\"id\":\"admin:groupon:update\",\"label\":\"编辑\",\"api\":\"POST /admin/groupon/update\"},{\"id\":\"admin:groupon:list\",\"label\":\"查询\",\"api\":\"GET /admin/groupon/list\"},{\"id\":\"admin:groupon:delete\",\"label\":\"删除\",\"api\":\"POST /admin/groupon/delete\"},{\"id\":\"admin:groupon:create\",\"label\":\"添加\",\"api\":\"POST /admin/groupon/create\"}]}]},{\"id\":\"商品管理\",\"label\":\"商品管理\",\"children\":[{\"id\":\"商品管理\",\"label\":\"商品管理\",\"children\":[{\"id\":\"admin:goods:read\",\"label\":\"详情\",\"api\":\"GET /admin/goods/detail\"},{\"id\":\"admin:goods:update\",\"label\":\"编辑\",\"api\":\"POST /admin/goods/update\"},{\"id\":\"admin:goods:list\",\"label\":\"查询\",\"api\":\"GET /admin/goods/list\"},{\"id\":\"admin:goods:delete\",\"label\":\"删除\",\"api\":\"POST /admin/goods/delete\"},{\"id\":\"admin:goods:create\",\"label\":\"上架\",\"api\":\"POST /admin/goods/create\"}]},{\"id\":\"评论管理\",\"label\":\"评论管理\",\"children\":[{\"id\":\"admin:comment:list\",\"label\":\"查询\",\"api\":\"GET /admin/comment/list\"},{\"id\":\"admin:comment:delete\",\"label\":\"删除\",\"api\":\"POST /admin/comment/delete\"}]}]},{\"id\":\"其他\",\"label\":\"其他\",\"children\":[{\"id\":\"权限测试\",\"label\":\"权限测试\",\"children\":[{\"id\":\"index:permission:write\",\"label\":\"权限写\",\"api\":\"POST /admin/index/write\"},{\"id\":\"index:permission:read\",\"label\":\"权限读\",\"api\":\"GET /admin/index/read\"}]}]},{\"id\":\"统计管理\",\"label\":\"统计管理\",\"children\":[{\"id\":\"用户统计\",\"label\":\"用户统计\",\"children\":[{\"id\":\"admin:stat:user\",\"label\":\"查询\",\"api\":\"GET /admin/stat/user\"}]},{\"id\":\"订单统计\",\"label\":\"订单统计\",\"children\":[{\"id\":\"admin:stat:order\",\"label\":\"查询\",\"api\":\"GET /admin/stat/order\"}]},{\"id\":\"商品统计\",\"label\":\"商品统计\",\"children\":[{\"id\":\"admin:stat:goods\",\"label\":\"查询\",\"api\":\"GET /admin/stat/goods\"}]}]}]");
        return s;
    }


    @PostMapping("role/permissions")
    public BaseRespVo rolePermissions(@RequestBody RolePermissionsUpdate rolePermissionsUpdate){
        if (rolePermissionsUpdate.getRoleId()==1){
            BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
            objectBaseRespVo.setErrmsg("当前角色的超级权限不能变更");
            objectBaseRespVo.setErrno(641);
            return objectBaseRespVo;
        }
        systemService.rolePermissionsUpdate(rolePermissionsUpdate);
        return BaseRespVo.ok();
    }


// ----------------------该接口 吴寒 已写-------------------------
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
