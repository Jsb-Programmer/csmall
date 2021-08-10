package com.fifth.controller;

import com.fifth.bean.BaseRespVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: first-git-springboot
 * @description:
 * @author: Liu
 * @create: 2021-08-10 19:32
 **/

@RestController
@RequestMapping("test")
public class GitTestController {

    @RequestMapping("jojo")
    public BaseRespVo jojo() {
        return BaseRespVo.ok("jojo test");
    }

}
