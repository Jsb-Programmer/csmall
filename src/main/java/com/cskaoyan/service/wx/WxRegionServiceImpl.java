package com.cskaoyan.service.wx;

import com.cskaoyan.bean.pojo.Region;
import com.cskaoyan.bean.pojo.RegionExample;
import com.cskaoyan.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName WxRegionServiceImpl
 * @Description TODO
 * @Author wpb
 * @Date 2021/8/15 15:00
 **/
@Service
public class WxRegionServiceImpl implements  WxRegionService {
    @Autowired
    RegionMapper regionMapper;
    @Override
    public List<Region> updateAddressList(Integer pid) {
        //1.回显省市区
        RegionExample example = new RegionExample();
        RegionExample.Criteria criteria = example.createCriteria();
       if (pid!=null&!"".equals(pid)){
            criteria.andPidEqualTo(pid);
        }
        List<Region> regions = regionMapper.selectByExample(example);
       // 2.修改省市区 再wx/address/save 里做
        return regions;
    }
}
