package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author Programmer
 * @Date 2021/8/11 21:34
 **/
public interface OrderService {
   BaseRespData query(Integer userId, Integer orderSn, ArrayList[] orderStatusArray, BaseParam param);
}
