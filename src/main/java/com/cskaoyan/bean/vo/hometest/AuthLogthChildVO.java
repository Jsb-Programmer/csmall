package com.cskaoyan.bean.vo.hometest;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName AuthLogthChildVO
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/14 15:32
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
public class AuthLogthChildVO {

    /**
     * unrecv : 2
     * uncomment : 0
     * unpaid : 9
     * unship : 0
     */

    private int unrecv;
    private int uncomment;
    private int unpaid;
    private int unship;


}
