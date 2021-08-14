package com.cskaoyan.service.admin;

import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.bo.market.BaseParamBO;
import com.cskaoyan.bean.bo.market.BrandCreateBO;
import com.cskaoyan.bean.bo.market.BrandDeleteBO;
import com.cskaoyan.bean.bo.market.BrandUpdateBO;
import com.cskaoyan.bean.vo.market.BaseRespDataVO;
import com.cskaoyan.bean.vo.market.BrandCreateVO;
import com.cskaoyan.bean.vo.market.BrandDeleteVO;
import com.cskaoyan.bean.vo.market.BrandUpdateVO;

public interface BrandService {
    BaseRespDataVO brandList(Integer id, String name, BaseParamBO baseParamBO);

    BrandCreateVO brandCreate(BrandCreateBO brandCreateBO);

    BrandUpdateVO brandUpdate(BrandUpdateBO brandUpdateBO);

    void brandDelete(BrandDeleteBO brandDeleteBO);
}
