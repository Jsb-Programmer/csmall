package com.cskaoyan.service.wx;

import com.cskaoyan.bean.pojo.Region;

import java.util.List;

/**
 * @ClassName WxRegionService
 * @Description TODO
 * @Author Programmer
 * @Date 2021/8/15 14:54
 **/
public interface WxRegionService {

    List<Region> updateAddressList(Integer pid);

}
