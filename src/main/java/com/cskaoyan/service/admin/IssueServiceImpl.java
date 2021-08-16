package com.cskaoyan.service.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.Issue;
import com.cskaoyan.bean.pojo.IssueExample;
import com.cskaoyan.mapper.IssueMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName IssueServiceImpl
 * @Description TODO
 * @Author Programmer
 * @Date 2021/8/12 23:41
 **/
@Service
public class IssueServiceImpl implements IssueService{
    @Autowired
    IssueMapper issueMapper;
    @Override
    public BaseRespData querry(BaseParam param, String question) {
        PageHelper.startPage(param.getPage(),param.getLimit());
        IssueExample example = new IssueExample();
        //构造排序
        example.setOrderByClause(param.getSort()+" "+param.getOrder());
        // 拼接条件
        IssueExample.Criteria criteria = example.createCriteria();
        if (question!=null&&!"".equals(question)){
            criteria.andQuestionLike("%"+question+"%");
        }
        List<Issue> items = issueMapper.selectByExample(example);
        PageInfo<Issue> issuePageInfo = new PageInfo<>(items);
        long total = issuePageInfo.getTotal();
        return BaseRespData.create(items,total);


    }

    @Override
    public BaseRespVo insertInto(Issue issue) {
        issueMapper.insertSelective(issue);
        return BaseRespVo.ok();
    }

    @Override
    public BaseRespVo updateQuestion(Issue issue) {
        issueMapper.updateByPrimaryKeySelective(issue);
        return BaseRespVo.ok();
    }

    @Override
    public BaseRespVo deleteQuestion(Issue issue) {
        issueMapper.deleteByPrimaryKey(issue.getId());
        return BaseRespVo.ok();
    }
}
