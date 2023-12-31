package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.home.WxIndexVO;
import com.cskaoyan.service.wx.HomeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/home")
public class WxHomeController {
    @Autowired
    HomeService homeService;

    @GetMapping("index")
    public BaseRespVo index() {
        WxIndexVO wxIndexVO = homeService.index();
        Subject subject = SecurityUtils.getSubject();

        return BaseRespVo.ok(wxIndexVO);
    }
}
