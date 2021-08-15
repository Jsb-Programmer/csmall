package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.Issue;
import com.cskaoyan.service.admin.IssueService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GeneralController
 * @Description TODO
 * @Author wpb
 * @Date 2021/8/12 21:00
 **/
@RestController
@RequestMapping("admin/issue")
public class GeneralController {
    @Autowired
    IssueService issueService;

    @RequiresPermissions("admin:issue:list")
    @RequestMapping("list")
    public BaseRespVo list(BaseParam param, String question) {
        BaseRespData data = issueService.querry(param, question);
        return BaseRespVo.ok(data);
    }

    @RequiresPermissions("admin:issue:create")
    @RequestMapping("create")
    public BaseRespVo create(@RequestBody Issue issue) {
        BaseRespVo data = issueService.insertInto(issue);
        return BaseRespVo.ok(data);
    }

    @RequiresPermissions("admin:issue:update")
    @RequestMapping("update")
    public BaseRespVo update(@RequestBody Issue issue) {
        BaseRespVo data = issueService.updateQuestion(issue);
        return BaseRespVo.ok(data);
    }

    @RequiresPermissions("admin:issue:delete")
    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Issue issue) {
        issueService.deleteQuestion(issue);
        return BaseRespVo.ok();

    }


}
