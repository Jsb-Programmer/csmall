package com.cskaoyan.service.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.pojo.GrouponRules;

/**
 * @author yangbo
 * @description
 * @date 2021/8/12 20:53
 */
public interface GrouponService {
    BaseRespData queryList(BaseParam baseParam, Integer goodsId);

    GrouponRules createGroupon(GrouponRules grouponRules);

    int updateGroupon(GrouponRules grouponRules);

    int deleteTopic(GrouponRules grouponRules);

    BaseRespData queryListRecord(BaseParam baseParam);
}
