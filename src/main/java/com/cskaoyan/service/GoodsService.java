package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.bo.CreateGoodBO;
import com.cskaoyan.bean.bo.UpdateGoodBO;
import com.cskaoyan.bean.pojo.Goods;
import com.cskaoyan.bean.vo.goods.CatAndBrandVO;
import com.cskaoyan.bean.vo.goods.GoodDetailVO;

public interface GoodsService {
    BaseRespData query(String goodsSn, String name, BaseParam baseParam);

    void create(CreateGoodBO createGoodBO);

    CatAndBrandVO catAndBrand();

    GoodDetailVO detail(int id);

    void update(UpdateGoodBO updateGoodBO);

    void delete(Goods goods);
}
