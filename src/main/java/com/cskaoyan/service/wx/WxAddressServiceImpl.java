package com.cskaoyan.service.wx;

import com.cskaoyan.bean.pojo.Address;
import com.cskaoyan.bean.pojo.Region;
import com.cskaoyan.bean.pojo.RegionExample;
import com.cskaoyan.bean.pojo.User;
import com.cskaoyan.bean.vo.wxAddressVo.WxAddressDetailVo;
import com.cskaoyan.mapper.AddressMapper;
import com.cskaoyan.mapper.RegionMapper;
import com.cskaoyan.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Integer userId = user.getId();
        //需要写死一个userId addressMapper.selectAddressByUserId(userId);
        List<Address> addresses = addressMapper.selectAddressByUserId();
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
        addressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public void delete(Address address) {
        addressMapper.deleteByPrimaryKey(address.getId());
    }
}
