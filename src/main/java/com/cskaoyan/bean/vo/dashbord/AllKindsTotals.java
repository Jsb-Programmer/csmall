package com.cskaoyan.bean.vo.dashbord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-11 23:36
 **/

@NoArgsConstructor
@Data
@AllArgsConstructor
public class AllKindsTotals {

    private Long goodsTotal;
    private Long userTotal;
    private Long productTotal;
    private Long orderTotal;

}
