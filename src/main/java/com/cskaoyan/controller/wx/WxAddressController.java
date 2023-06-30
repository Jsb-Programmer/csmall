package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.Address;
import com.cskaoyan.bean.pojo.User;
import com.cskaoyan.bean.vo.wxAddressVo.WxAddressDetailVo;
import com.cskaoyan.service.wx.WxAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName WxAddressController
 * @Description TODO
 * @Author wpb
 * @Date 2021/8/14 14:56
 **/
@RestController
@RequestMapping("wx/address")
public class WxAddressController {
  @Autowired
    WxAddressService wxAddressService;
  @RequestMapping("list")
    public BaseRespVo list(User user){
        List<Address> data = wxAddressService.qurry(user);
        return BaseRespVo.ok(data);
    }
    @RequestMapping("detail")
    public BaseRespVo detail(Integer id){
        WxAddressDetailVo data = wxAddressService.detail(id);
        return BaseRespVo.ok(data);
    }
    //post
    @RequestMapping("save")
    public BaseRespVo save(@RequestBody Address address){
         wxAddressService.save(address);
        return BaseRespVo.ok(address.getId());
    }
    //post
    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Address address)
    {
        wxAddressService.delete(address);
        return BaseRespVo.ok();
    }
}
