package com.cskaoyan.service.wx;

import com.cskaoyan.bean.bo.cart.AddBO;
import com.cskaoyan.bean.bo.cart.Checked;
import com.cskaoyan.bean.bo.cart.UpdateBO;
import com.cskaoyan.bean.vo.cart.CartIndex;
import com.cskaoyan.bean.vo.cart.Index;

import java.util.List;

public interface CartService {
    Index userIndex(Integer userId);

    CartIndex index(Integer userId);

    CartIndex checked(Checked checked, Integer userId);

    int add(AddBO addBO, Integer userId);

    CartIndex delete(List<Integer> productIds, Integer userId);

    int update(UpdateBO updateBO);
}
