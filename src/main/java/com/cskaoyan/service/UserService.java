package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.bo.user.ReceivedAddressBO;
import com.cskaoyan.bean.pojo.*;

public interface UserService {
    BaseRespData<User> queryUsers(String username,String mobile,BaseParam baseParam);

    BaseRespData<Collect> queryCollects(String userId, String valueId, BaseParam baseParam);

    BaseRespData<FootPrint> queryFootPrints(String userId, String goodsId, BaseParam baseParam);

    BaseRespData<SearchHistory> querySearchHistory(String userId, String keyword, BaseParam baseParam);

    BaseRespData<Feedback> queryFeedbacks(String username, String id, BaseParam baseParam);

    BaseRespData<ReceivedAddressBO> queryAddress(String name, String userId, BaseParam baseParam);
}
