package com.cskaoyan.bean.vo.market;

import lombok.Data;

/**
 * Vo viewObject
 * @param <T>
 * 这是前后端分离，json数据一个常用的格式
 */
@Data
public class BaseRespVo<T> {
    T data;
    String errmsg;//告诉前端请求的消息
    long errno;//自己的前后端应用 ： 自定义的状态码 → 通常前端根据该状态码做不同的处理

    public static BaseRespVo ok(){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
    public static BaseRespVo ok(Object data){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(data);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
    public static BaseRespVo ok(Object data, String msg){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(data);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg(msg);
        return baseRespVo;
    }
    public static BaseRespVo fail(){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(500);
        baseRespVo.setErrmsg("失败");
        return baseRespVo;
    }
    public static BaseRespVo fail(String msg){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(500);
        baseRespVo.setErrmsg(msg);
        return baseRespVo;
    }

    public static BaseRespVo fail(int num,String msg){
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(num);
        baseRespVo.setErrmsg(msg);
        return baseRespVo;
    }
}
