package com.cskaoyan.service.wx;

import com.cskaoyan.bean.pojo.Feedback;
import com.cskaoyan.bean.pojo.User;
import com.cskaoyan.mapper.FeedbackMapper;
import com.cskaoyan.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-16 12:48
 **/

@Service
public class WxFeedbackServiceImpl implements WxFeedbackService{
    @Autowired
    FeedbackMapper feedbackMapper;
    @Autowired
    UserMapper userMapper;

    /**
     * 添加反馈信息
     * @param feedback
     * @return
     */
    @Override
    @Transactional
    public int addFeedback(Feedback feedback) {
        Integer userId;
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            return 400;
        }
        userId = (Integer) subject.getPrincipal();
        User user = userMapper.selectByPrimaryKey(userId);
        feedback.setAddTime(new Date());
        feedback.setUserId(userId);
        feedback.setUsername(user.getUsername());
        feedbackMapper.insertSelective(feedback);
        return 200;
    }
}
