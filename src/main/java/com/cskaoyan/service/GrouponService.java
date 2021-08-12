package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.pojo.GrouponRules;

/**
 * @author yangbo
 * @description
 * @date 2021/8/12 20:53
 */
public interface GrouponService {
    BaseRespData queryList(BaseParam baseParam);

    GrouponRules createGroupon(GrouponRules grouponRules);

    int updateGroupon(GrouponRules grouponRules);

    int deleteTopic(GrouponRules grouponRules);
}
