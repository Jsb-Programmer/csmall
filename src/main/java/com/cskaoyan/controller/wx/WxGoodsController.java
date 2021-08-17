package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.WxListBaseParam;
import com.cskaoyan.bean.pojo.Goods;
import com.cskaoyan.bean.vo.goods.WxCategoryVO;
import com.cskaoyan.bean.vo.goods.WxDetailVO;
import com.cskaoyan.bean.vo.goods.WxGoodsListVO;
import com.cskaoyan.service.admin.GoodsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx/goods")
public class WxGoodsController {
    @Autowired
    GoodsService goodsService;

    /**
     * 查询商品数量
     * @return 响应
     */
    @GetMapping("count")
    public BaseRespVo count() {
        Integer count = goodsService.count();
        Map<String, Integer> map = new HashMap<>();
        map.put("goodsCount", count);
        return BaseRespVo.ok(map);
    }

    @GetMapping("list")
    public BaseRespVo list(WxListBaseParam wxListBaseParam, HttpServletResponse response) {
//        ----------------------------------------------------------------------------
//        Cookie cookie = new Cookie("keyword",wxListBaseParam.getKeyword());
//        response.addCookie(cookie);
//        ---------------------------------------------------------------------------------
        WxGoodsListVO wxGoodsListVO = goodsService.list(wxListBaseParam);
        return BaseRespVo.ok(wxGoodsListVO);
    }

    @GetMapping("category")
    public BaseRespVo category(Integer id) {
        WxCategoryVO wxCategoryVO = goodsService.category(id);
        return BaseRespVo.ok(wxCategoryVO);
    }

    @GetMapping("detail")
    public BaseRespVo detail(Integer id) {
        WxDetailVO wxDetailVO = goodsService.detailForWx(id);
        return BaseRespVo.ok(wxDetailVO);
    }

    @GetMapping("related")
    public BaseRespVo related(Integer id) {
        List<Goods> relatedGoods = goodsService.related(id);
        Map<String, List<Goods>> relatedMap = new HashMap<>();
        relatedMap.put("goodsList", relatedGoods);
        return BaseRespVo.ok(relatedMap);
    }
}
