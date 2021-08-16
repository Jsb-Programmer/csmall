package com.cskaoyan.service.wx;

import com.cskaoyan.bean.pojo.Collect;
import com.cskaoyan.bean.vo.wxCollectVo.WxCollectAddVo;
import com.cskaoyan.bean.vo.wxCollectVo.WxCollectListVo;
import com.cskaoyan.bean.vo.wxCollectVo.WxCollectVo;

import java.util.List;

public interface WxCollectService {

   WxCollectVo querry(Integer type, Integer page, Integer size);

   WxCollectAddVo addordelete(Collect collect);
    
}
