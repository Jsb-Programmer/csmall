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

    @RequestMapping("wuhan")
    public BaseRespVo wuhan() {
        return BaseRespVo.ok("jojo, 我dio不做人啦！");
    }
    @RequestMapping("gaigai")
    public BaseRespVo gaigai() {
        return BaseRespVo.ok("我只爱gaigai");
    }
    @RequestMapping("zs")
    public BaseRespVo zs() {
        return BaseRespVo.ok("zszszs");
    }

    @RequestMapping("wuyuqi")
    public BaseRespVo wuyuqi() {
        return BaseRespVo.ok("吴瑜琦");
    }

}
