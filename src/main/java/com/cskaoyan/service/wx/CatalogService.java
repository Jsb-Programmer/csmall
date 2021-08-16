package com.cskaoyan.service.wx;

import com.cskaoyan.bean.vo.brandcs.CatalogCurrentVO;
import com.cskaoyan.bean.vo.brandcs.CatalogIndexVO;

/**
 * @ClassName CatalogService
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/14 11:13
 * @Version 1.0
 **/
public interface CatalogService {
    CatalogCurrentVO catalogCurrent(Integer id);

    CatalogIndexVO catalogIndex();

}
