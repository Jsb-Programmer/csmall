package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.bo.cart.*;
import com.cskaoyan.bean.vo.cart.CartIndex;
import com.cskaoyan.bean.vo.cart.CheckoutVO;
import com.cskaoyan.bean.vo.cart.Index;
import com.cskaoyan.bean.vo.cart.Order;
import com.cskaoyan.service.wx.CartService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx")

public class CartController {

    @Autowired
    CartService cartService;


    /**
     * 获取user的订单状态表
     * @return
     */
    @RequestMapping("user/index")
    public BaseRespVo userIndex(){
        // TODO: 2021/8/14  userId 暂时指定为 1
        Subject subject = SecurityUtils.getSubject();
        Integer userId = ((Integer) subject.getPrincipal());
        Index index = cartService.userIndex(userId);
        Order order = new Order();
        order.setOrder(index);
        return BaseRespVo.ok(order);
    }

    /**
     * 购物车首页显示
     * @return
     */
    @RequestMapping("cart/index")
    public BaseRespVo index(){
        // TODO: 2021/8/14  userId 暂时指定为 1
        Subject subject = SecurityUtils.getSubject();
        Integer userId = ((Integer) subject.getPrincipal());
        CartIndex cartIndex = cartService.index(userId);
        return BaseRespVo.ok(cartIndex);
    }


    /**
     * 修改购物车中商品的checked
     * @return
     */
    @RequestMapping("cart/checked")
    public BaseRespVo checked(@RequestBody Checked checked){
        // TODO: 2021/8/14  userId 暂时指定为 1
        Subject subject = SecurityUtils.getSubject();
        Integer userId = ((Integer) subject.getPrincipal());
        CartIndex cartIndex = cartService.checked(checked,userId);
        return BaseRespVo.ok(cartIndex);
    }


    /**
     * cart的增加
     * @param
     * @return
     */
    @RequestMapping("cart/add")
    public BaseRespVo add(@RequestBody AddBO addBO){
        // TODO: 2021/8/14  userId 暂时指定为 1
        Subject subject = SecurityUtils.getSubject();
        Integer userId = ((Integer) subject.getPrincipal());
        int i = cartService.add(addBO,userId);
        return BaseRespVo.ok(i);
    }


    /**
     * 购物车下单
     * @param
     * @return
     */
    @RequestMapping("cart/checkout")
    public BaseRespVo checkout( CheckoutBO checkoutBO){
        // TODO: 2021/8/14  userId 暂时指定为 1
        Subject subject = SecurityUtils.getSubject();
        Integer userId = ((Integer) subject.getPrincipal());
        CheckoutVO checkoutVO = cartService.checkout(checkoutBO,userId);
        return BaseRespVo.ok(checkoutVO);
    }


    /**
     * 逻辑删除cart中的商品
     * @param
     * @return
     */
    @RequestMapping("cart/delete")
    public BaseRespVo delete(@RequestBody DeleteBo deleteBo){
        // TODO: 2021/8/14  userId 暂时指定为 1
        Subject subject = SecurityUtils.getSubject();
        Integer userId = ((Integer) subject.getPrincipal());
        CartIndex delete = cartService.delete(deleteBo.getProductIds(), userId);
        return BaseRespVo.ok(delete);
    }


    /**
     * 修cart中商品的数量
     * @param updateBO
     * @return
     */
    @RequestMapping("cart/update")
    public BaseRespVo update(@RequestBody UpdateBO updateBO){
        // TODO: 2021/8/14  userId 暂时指定为 1
        Subject subject = SecurityUtils.getSubject();
        Integer userId = ((Integer) subject.getPrincipal());
        int update = cartService.update(updateBO);
        return BaseRespVo.ok();
    }


    /**
     * 购物车快速加入
     * @param addBO
     * @return
     */
    @RequestMapping("cart/fastadd")
    public BaseRespVo fastadd(@RequestBody AddBO addBO){
        // TODO: 2021/8/14  userId 暂时指定为 1
        Subject subject = SecurityUtils.getSubject();
        Integer userId = ((Integer) subject.getPrincipal());
        int fastadd = cartService.fastadd(addBO,userId);
        return BaseRespVo.ok(305);
    }


    /**
     * 该请求不携带任何参数,,,不知道这个请求是干什么的
     * @return
     */
    @RequestMapping("cart/goodscount")
    public BaseRespVo goodscount(){
        // TODO: 2021/8/14  userId 暂时指定为 1
        Subject subject = SecurityUtils.getSubject();
        Integer userId = ((Integer) subject.getPrincipal());
        int goodscount = cartService.goodscount(userId);
        return BaseRespVo.ok(goodscount);
    }



}
