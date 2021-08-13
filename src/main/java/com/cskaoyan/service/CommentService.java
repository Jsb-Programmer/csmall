package com.cskaoyan.service;

import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.CommentParam;
import com.cskaoyan.bean.bo.comment.DeleteCommentBO;

public interface CommentService {

    BaseRespData query(CommentParam commentParam);

    void delete(DeleteCommentBO deleteCommentBO);
}
