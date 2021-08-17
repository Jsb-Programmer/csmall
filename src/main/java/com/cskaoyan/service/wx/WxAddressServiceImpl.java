package com.cskaoyan.service.wx;

import com.cskaoyan.bean.pojo.Address;
import com.cskaoyan.bean.pojo.Region;
import com.cskaoyan.bean.pojo.RegionExample;
import com.cskaoyan.bean.pojo.User;
import com.cskaoyan.bean.vo.wxAddressVo.WxAddressDetailVo;
import com.cskaoyan.mapper.AddressMapper;
import com.cskaoyan.mapper.RegionMapper;
import com.cskaoyan.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName WxAddressServiceImpl
 * @Description TODO
 * @Author wpb
 * @Date 2021/8/14 14:58
 **/

@Service
public class WxAddressServiceImpl implements WxAddressService{
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RegionMapper regionMapper;
    @Override
    public List<Address> qurry(User user) {

        //根据use_id再去查用户对应的收货信息。

        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();
        //需要写死一个userId addressMapper.selectAddressByUserId(userId);
        List<Address> addresses = addressMapper.selectAddressByUserId(userId);
        return addresses;
    }
    @Override
    public WxAddressDetailVo detail(Integer id) {
        WxAddressDetailVo wxAddressDetailVo = addressMapper.selectAdDetailById(id);
        return wxAddressDetailVo;
    }
    @Override
    public void save(Address address) {
        //修改adderess表信息 sql by wpb
        if (address.getId() != null) {
            // 获得userId
            Subject subject = SecurityUtils.getSubject();
            Integer userId = (Integer) subject.getPrincipal();
            address.setUserId(userId);
            address.setAddTime(new Date());
            address.setUpdateTime(new Date());

            addressMapper.insertSelective(address);
        }
        addressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public void delete(Address address) {
        addressMapper.deleteByPrimaryKey(address.getId());
    }
}
