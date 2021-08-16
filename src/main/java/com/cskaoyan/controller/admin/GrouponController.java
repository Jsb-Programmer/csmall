package com.cskaoyan.controller.admin;

/**
 * @author yangbo
 * @description
 * @date 2021/8/12 20:51
 */

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.GrouponRules;
import com.cskaoyan.service.admin.GrouponService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Groupon模块
 */
@RestController
@RequestMapping("admin/groupon")
public class GrouponController {

    @Autowired
    GrouponService grouponService;

    /**
     * 查看全部团购规则信息
     */
    @RequiresPermissions("admin:groupon:list")
    @GetMapping("/list")
    public BaseRespVo list(BaseParam baseParam){

        BaseRespData data = grouponService.queryList(baseParam);
        return BaseRespVo.ok(data);
    }

    /**
     * 增加新的团购信息
     */
    @RequiresPermissions("admin:groupon:create")
    @PostMapping("create")
    public BaseRespVo create(@RequestBody GrouponRules grouponRules){

        grouponRules = grouponService.createGroupon(grouponRules);
        return BaseRespVo.ok(grouponRules);
    }

    /**
     * 查看团购记录信息
     */
    @RequiresPermissions("admin:groupon:read")
    @GetMapping("/listRecord")
    public BaseRespVo listRecord(BaseParam baseParam){

        BaseRespData data = grouponService.queryListRecord(baseParam);
        return BaseRespVo.ok(data);
    }

    /**
     * 更新团购信息
     */
    @RequiresPermissions("admin:groupon:update")
    @PostMapping("update")
    public BaseRespVo update(@RequestBody GrouponRules grouponRules){

        int code = grouponService.updateGroupon(grouponRules);

        if (code ==1){
            return BaseRespVo.ok();
        }else {
            return BaseRespVo.fail();
        }
    }

    /**
     * 删除团购信息
     */
    @RequiresPermissions("admin:groupon:delete")
    @PostMapping("delete")
    public BaseRespVo delete(@RequestBody GrouponRules grouponRules){
        int code = grouponService.deleteTopic(grouponRules);
        if (code ==1){
            return BaseRespVo.ok();
        }else {
            return BaseRespVo.fail();
        }
    }
}
