package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.CommentParam;
import com.cskaoyan.bean.bo.comment.DeleteCommentBO;
import com.cskaoyan.service.admin.CommentService;
import com.cskaoyan.utils.ValidationUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("admin/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    /**
     * 评论获取
     * @param commentParam 前段获取的数据
     * @param bindingResult 参数校验
     * @return 评论
     */
    @RequiresPermissions("admin:comment:list")
    @GetMapping("list")
    public BaseRespVo list(@Valid CommentParam commentParam, BindingResult bindingResult) {
        String message = ValidationUtil.dealWithFieldError(bindingResult);
        if (message != null)
            return BaseRespVo.fail(message);

        BaseRespData baseRespData = commentService.query(commentParam);
        return BaseRespVo.ok(baseRespData);
    }

    /**
     * 删除数据
     * @param deleteCommentBO 具体删除的评论
     * @return 删除成功响应
     */
    @RequiresPermissions("admin:comment:delete")
    @PostMapping("delete")
    public BaseRespVo delete(@RequestBody DeleteCommentBO deleteCommentBO) {
        commentService.delete(deleteCommentBO);
        return BaseRespVo.ok();
    }
}
