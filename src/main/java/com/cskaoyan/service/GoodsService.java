package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;

public interface GoodsService {
    BaseRespData query(String goodsSn, String name, BaseParam baseParam);
}
