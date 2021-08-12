package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.pojo.Ad;
import org.springframework.stereotype.Service;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-11 18:40
 **/


public interface AdService {
    BaseRespData queryList(String name, String content, BaseParam baseParam);

    Ad  createAd(Ad ad);

    Ad updateAd(Ad ad);

    int deleteAd(Ad ad);
}
