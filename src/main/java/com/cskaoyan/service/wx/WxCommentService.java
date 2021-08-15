package com.cskaoyan.service.wx;

import com.cskaoyan.bean.BaseRespData2;
import com.cskaoyan.bean.WxListBaseParam2;
/**
 * @author yangbo
 * @description
 * @date 2021/8/15 17:43
 */
public interface WxCommentService {

    BaseRespData2 queryList(Integer valueId, WxListBaseParam2 commentBaseParam, Integer type, Integer showType);
}
