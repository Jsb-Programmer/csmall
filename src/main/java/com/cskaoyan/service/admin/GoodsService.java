package com.cskaoyan.service.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.WxListBaseParam;
import com.cskaoyan.bean.bo.goods.CreateGoodBO;
import com.cskaoyan.bean.bo.goods.UpdateGoodBO;
import com.cskaoyan.bean.pojo.Goods;
import com.cskaoyan.bean.vo.goods.*;

import java.util.List;

public interface GoodsService {
    BaseRespData query(String goodsSn, String name, BaseParam baseParam);

    void create(CreateGoodBO createGoodBO);

    CatAndBrandVO catAndBrand();

    GoodDetailVO detail(int id);

    void update(UpdateGoodBO updateGoodBO);

    void delete(Goods goods);

    Integer count();

    WxGoodsListVO list(WxListBaseParam wxListBaseParam);

    WxCategoryVO category(Integer id);

    WxDetailVO detailForWx(Integer id);

    List<Goods> related(Integer id);
}
