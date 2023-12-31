package com.cskaoyan.service.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.Issue;

import javax.xml.ws.Service;

public interface IssueService {
    BaseRespData querry(BaseParam param, String question);

    Issue insertInto(Issue issue);

    BaseRespVo updateQuestion(Issue issue);

    BaseRespVo deleteQuestion(Issue issue);

}
