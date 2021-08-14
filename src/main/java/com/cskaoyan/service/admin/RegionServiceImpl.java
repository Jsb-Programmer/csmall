package com.cskaoyan.service.admin;

import com.cskaoyan.bean.pojo.Region;
import com.cskaoyan.bean.pojo.RegionExample;
import com.cskaoyan.bean.vo.market.City;
import com.cskaoyan.bean.vo.market.County;
import com.cskaoyan.bean.vo.market.Province;
import com.cskaoyan.mapper.RegionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RegionServiceImpl
 * @Description 行政区域
 * @Author 王昀昊
 * @Date 2021/8/11 17:49
 * @Version 1.0
 **/
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionMapper regionMapper;

    /**
     * 回显行政区域
     * @return
     */
    @Override
    public List<Province> regionList() {
        RegionExample example = new RegionExample();
        List<Province> provinceList = new ArrayList<>();
        List<City> cityList = new ArrayList<>();
        List<County> countyList = new ArrayList<>();

        //查询到数据库的所有信息
        List<Region> regionList = regionMapper.selectByExample(example);

        for (Region region : regionList) {
            if (region.getType() == 3){
                County county = new County();
//                county.setId(region.getId());
//                county.setName(region.getName());
//                county.setCode(region.getCode());
//                county.setType(region.getType());
//                county.setPid(region.getPid());
                BeanUtils.copyProperties(region,county);
                countyList.add(county);
            }
        }
        for (Region region : regionList) {
            if (region.getType() == 2){
                City city = new City();
//                city.setId(region.getId());
//                city.setName(region.getName());
//                city.setCode(region.getCode());
//                city.setType(region.getType());
//                city.setPid(region.getPid());
                BeanUtils.copyProperties(region,city);
                List<County> countyList2 = new ArrayList<>();
                for (County county : countyList) {
                    if (region.getId() == county.getPid()){
                        countyList2.add(county);
                    }
                }
                city.setChildren(countyList2);
                cityList.add(city);
            }
        }
        for (Region region : regionList) {
            if (region.getType() == 1){
                Province province = new Province();
//                province.setId(region.getId());
//                province.setName(region.getName());
//                province.setCode(region.getCode());
//                province.setType(region.getType());
                BeanUtils.copyProperties(region,province);
                List<City> cityList2 = new ArrayList<>();
                for (City city : cityList) {
                    if (region.getId() == city.getPid()){
                        cityList2.add(city);
                    }
                }
                province.setChildren(cityList2);
                provinceList.add(province);
            }
        }
        return provinceList;
    }
}
