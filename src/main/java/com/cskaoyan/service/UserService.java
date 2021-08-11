package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;

public interface UserService {
    BaseRespData query(String username, String mobile, BaseParam param);
}
