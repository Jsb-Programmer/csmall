package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.CommentParam;
import com.cskaoyan.bean.bo.comment.DeleteCommentBO;
import com.cskaoyan.service.CommentService;
import com.cskaoyan.utils.ValidationUtil;
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
    @PostMapping("delete")
    public BaseRespVo delete(@RequestBody DeleteCommentBO deleteCommentBO) {
        commentService.delete(deleteCommentBO);
        return BaseRespVo.ok();
    }
}
