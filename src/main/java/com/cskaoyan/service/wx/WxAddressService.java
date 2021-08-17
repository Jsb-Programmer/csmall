package com.cskaoyan.service.wx;


import com.cskaoyan.bean.pojo.Ad;
import com.cskaoyan.bean.pojo.Address;
import com.cskaoyan.bean.pojo.Region;
import com.cskaoyan.bean.pojo.User;
import com.cskaoyan.bean.vo.wxAddressVo.WxAddressDetailVo;

import java.util.List;

public interface WxAddressService {


     List<Address>  qurry(User user);

     WxAddressDetailVo detail(Integer id);

     void save(Address address);

     void delete(Address address);

}
