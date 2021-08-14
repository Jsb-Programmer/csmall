package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.bo.goods.CreateGoodBO;
import com.cskaoyan.bean.bo.goods.UpdateGoodBO;
import com.cskaoyan.bean.pojo.Goods;
import com.cskaoyan.bean.vo.goods.CatAndBrandVO;
import com.cskaoyan.bean.vo.goods.GoodDetailVO;
import com.cskaoyan.service.admin.GoodsService;
import com.cskaoyan.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("admin/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    /**
     * 商品列表
     * @param goodsSn 商品编号
     * @param name  商品名称
     * @param baseParam
     * @return
     */
    @GetMapping("list")
    public BaseRespVo list(String goodsSn, String name, BaseParam baseParam) {
        BaseRespData baseRespData = goodsService.query(goodsSn, name, baseParam);
        return BaseRespVo.ok(baseRespData);
    }

    @PostMapping("create")
    public BaseRespVo create(@Valid @RequestBody CreateGoodBO createGoodBO, BindingResult bindingResult) {
        String message = ValidationUtil.dealWithFieldError(bindingResult);
        // 如果参数校验不通过则返回fail
        if (message != null)
            return BaseRespVo.fail(message);

        goodsService.create(createGoodBO);
        return BaseRespVo.ok();
    }

    @GetMapping("catAndBrand")
    public BaseRespVo catAndBrand() {
        CatAndBrandVO catAndBrandVO = goodsService.catAndBrand();
        return BaseRespVo.ok(catAndBrandVO);
    }

    @GetMapping("detail")
    public BaseRespVo detail(int id) {
        GoodDetailVO goodDetailVO = goodsService.detail(id);
        return BaseRespVo.ok(goodDetailVO);
    }

    @PostMapping("update")
    public BaseRespVo update(@Validated @RequestBody UpdateGoodBO updateGoodBO, BindingResult bindingResult) {
        String message = ValidationUtil.dealWithFieldError(bindingResult);
        if (message != null)
            return BaseRespVo.fail(message);

        goodsService.update(updateGoodBO);
        return BaseRespVo.ok();
    }

    @PostMapping("delete")
    public BaseRespVo delete(@RequestBody Goods goods) {
        goodsService.delete(goods);
        return BaseRespVo.ok();
    }
}
