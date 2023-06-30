package com.cskaoyan.service.wx;

import com.cskaoyan.bean.pojo.*;
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
        Subject subject1 = SecurityUtils.getSubject();
        Integer userId = (Integer) subject1.getPrincipal();
        // 判断是否设置了默认地址
        // 1.修改地址
        if (address.getIsDefault() == true) {
            // 获取该用户的所有address

            AddressExample addressExample = new AddressExample();
            AddressExample.Criteria criteria = addressExample.createCriteria();
            criteria.andUserIdEqualTo(userId);
            List<Address> addresses = addressMapper.selectByExample(addressExample);
            // 遍历所有的地址，查看是否有默认地址
            for (Address address1 : addresses) {
                if (address1.getIsDefault() == true) {
                    // 更新这个address为非默认
                    address1.setIsDefault(false);
                    addressMapper.updateByPrimaryKeySelective(address1);
                }
            }
        }
        //修改adderess表信息 sql by wpb
        // 2.新建地址
        if (address.getId() == 0||address.getId() ==null) {
            // 获得userId
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
