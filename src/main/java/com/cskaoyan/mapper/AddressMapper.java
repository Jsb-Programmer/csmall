package com.cskaoyan.mapper;

import com.cskaoyan.bean.pojo.Address;
import com.cskaoyan.bean.pojo.AddressExample;
import com.cskaoyan.bean.vo.wxAddressVo.WxAddressDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {
    long countByExample(AddressExample example);

    int deleteByExample(AddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    List<Address> selectByExample(AddressExample example);

    Address selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByExample(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    // 新加查询 wpb
    List<Address> selectAddressByUserId(Integer userId);

    // 新加wx/address/detail
    WxAddressDetailVo selectAdDetailById(Integer id);


}