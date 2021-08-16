package com.cskaoyan.service.wx;

import com.cskaoyan.bean.bo.wxfootprint.FootprintPageBO;

public interface WxFootprintService {
    FootprintPageBO footprintList(Integer page,Integer size);

    void deleteFootprintById(Integer id);
}
