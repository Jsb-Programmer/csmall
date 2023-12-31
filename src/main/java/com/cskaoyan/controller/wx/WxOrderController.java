package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.bo.wxOrder.OrderCommentBo;
import com.cskaoyan.bean.bo.wxOrder.SubmitBo;
import com.cskaoyan.bean.bo.wxOrder.WxOrderBaseParamBO;
import com.cskaoyan.bean.pojo.Order;
import com.cskaoyan.bean.pojo.OrderGoods;
import com.cskaoyan.bean.pojo.WxOrderGoods;
import com.cskaoyan.bean.vo.wxOrder.OrderDetailDataVo;
import com.cskaoyan.bean.vo.wxOrder.SubmitVo;
import com.cskaoyan.bean.vo.wxOrder.WxOrderBaseRespVo;
import com.cskaoyan.service.wx.WxOrderService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: mall
 * @description: wx订单
 * @author: Liu
 * @create: 2021-08-14 14:23
 **/

@RestController
@RequestMapping("wx/order")
public class WxOrderController {

    @Autowired
    WxOrderService wxOrderService;



    /**
     * 显示订单list
     */
    @RequestMapping("list")
    public BaseRespVo orderList(Integer showType, WxOrderBaseParamBO baseParamBO) {
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (authenticated ==false) {
            return BaseRespVo.fail("请登录", 501);
        }
        WxOrderBaseRespVo data = wxOrderService.getOrderList(showType, baseParamBO);
        if (data == null) {
            return null;
        }
        return BaseRespVo.ok(data);
    }

    /**
     * 显示订单详情
     */
    @RequestMapping("detail")
    public BaseRespVo orderDetail(Integer orderId) {
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (authenticated ==false) {
            return BaseRespVo.fail("请登录", 501);
        }
        OrderDetailDataVo data = wxOrderService.getOrderDetail(orderId);
        return BaseRespVo.ok(data);
    }

    /**
     * 取消订单
     */
    @RequestMapping("cancel")
    public BaseRespVo cancelOrder(@RequestBody Order order) {
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (authenticated ==false) {
            return BaseRespVo.fail("请登录", 501);
        }
        int affectRows = wxOrderService.cancelOrder(order);
        if (affectRows == 1) {
            return BaseRespVo.ok();
        } else {
            return BaseRespVo.fail("取消失败，请联系客服");
        }
    }

    /**
     * 申请退货
     */
    @RequestMapping("refund")
    public BaseRespVo refundOrder(@RequestBody Order order) {
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (authenticated ==false) {
            return BaseRespVo.fail("请登录", 501);
        }
        int affectRows = wxOrderService.refundOrder(order);
        if (affectRows == 1) {
            return BaseRespVo.ok();
        } else {
            return BaseRespVo.fail("退货失败，请联系客服");
        }
    }

    /**
     * 删除订单
     */
    @RequestMapping("delete")
    public BaseRespVo deleteOrder(@RequestBody Order order) {
        // 判断登录
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (authenticated ==false) {
            return BaseRespVo.fail("请登录", 501);
        }
        int affectRows = wxOrderService.deleteOrder(order);
        if (affectRows == 1) {
            return BaseRespVo.ok();
        } else {
            return BaseRespVo.fail("删除失败，请联系客服");
        }
    }

    /**
     * 显示商品评论界面
     */
    @RequestMapping("goods")
    public BaseRespVo goods(Integer orderId, Integer goodsId) {
        // 判断登录
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (authenticated ==false) {
            return BaseRespVo.fail("请登录", 501);
        }
        WxOrderGoods data = wxOrderService.goods(orderId, goodsId);
        return BaseRespVo.ok(data);
    }

    /**
     * 提交商品评论
     */
    @RequestMapping("comment")
    public BaseRespVo comment(@RequestBody OrderCommentBo orderCommentBo) {
        // 判断登录
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (authenticated ==false) {
            return BaseRespVo.fail("请登录", 501);
        }
        wxOrderService.comment(orderCommentBo);
        return BaseRespVo.ok();
    }

    /**
     * 提交商品评论
     */
    @RequestMapping("confirm")
    public BaseRespVo confirm(@RequestBody Order order) {
        // 判断登录
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (authenticated ==false) {
            return BaseRespVo.fail("请登录", 501);
        }
        wxOrderService.confirm(order);
        return BaseRespVo.ok();
    }

    /**
     * 购买
     */
    @RequestMapping("submit")
    public BaseRespVo submit(@RequestBody SubmitBo submitBo) {
        // 判断登录
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (authenticated ==false) {
            return BaseRespVo.fail("请登录", 501);
        }
        SubmitVo data = wxOrderService.submit(submitBo);
        return BaseRespVo.ok(data);
    }

    /**
     * 付款
     */
    @RequestMapping("prepay")
    public BaseRespVo prepay(Integer orderId, HttpServletResponse response) throws IOException {
        // 判断登录
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (authenticated ==false) {
            return BaseRespVo.fail("请登录", 501);
        }
//        wxOrderService.submit(submitBo);
        //todo:重定向地址
//        response.sendRedirect("f:/王道/7. spring/project2/cskaoyan-wx/config/api");
        return BaseRespVo.fail("付款失败", 724);
    }


}
