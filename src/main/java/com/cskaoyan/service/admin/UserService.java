package com.cskaoyan.service.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.bo.auth.RegisterUserBO;
import com.cskaoyan.bean.bo.auth.ResetBO;
import com.cskaoyan.bean.bo.user.ReceivedAddressBO;
import com.cskaoyan.bean.bo.user.WxUserLoginBO;
import com.cskaoyan.bean.pojo.*;

public interface UserService {
    BaseRespData<User> queryUsers(String username,String mobile,BaseParam baseParam);

    BaseRespData<Collect> queryCollects(String userId, String valueId, BaseParam baseParam);

    BaseRespData<FootPrint> queryFootPrints(String userId, String goodsId, BaseParam baseParam);

    BaseRespData<SearchHistory> querySearchHistory(String userId, String keyword, BaseParam baseParam);

    BaseRespData<Feedback> queryFeedbacks(String username, String id, BaseParam baseParam);

    BaseRespData<ReceivedAddressBO> queryAddress(String name, String userId, BaseParam baseParam);

    WxUserLoginBO userLoginInfo(String username, String password) throws Exception;

    int addUser(RegisterUserBO registerUserBO) throws Exception;

    int resetPsw(ResetBO registerUserBO) throws Exception;
}
