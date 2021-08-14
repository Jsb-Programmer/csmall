package com.cskaoyan.service.admin;

import com.cskaoyan.bean.vo.config.ExpressConfigVO;
import com.cskaoyan.bean.vo.config.MallConfigVO;
import com.cskaoyan.bean.vo.config.OrderConfigVO;
import com.cskaoyan.bean.vo.config.WxConfigVO;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-12 15:14
 **/
public interface ConfigService {
    MallConfigVO getMallConfig();

    int postMallConfig(MallConfigVO mallConfigVO);

    ExpressConfigVO getExpressConfig();

    int postExpressConfig(ExpressConfigVO expressConfigVO);

    OrderConfigVO getOrderConfig();

    int postOrderConfig(OrderConfigVO orderConfigVO);

    WxConfigVO getWxConfig();

    int postWxConfig(WxConfigVO wxConfigVO);
}
