package com.cskaoyan.exception;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.storage.ImgUploadVO;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @ClassName GlobalExceptionControllerAdvice
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/13 14:25
 * @Version 1.0
 **/
@RestControllerAdvice
public class GlobalExceptionControllerAdvice {
    @Value("${img.failUrl}")
    String url;
    @ExceptionHandler(FileSizeLimitExceededException.class)
    public BaseRespVo fileUploadExceptionHandler() {
        ImgUploadVO imgUploadVO = new ImgUploadVO();
        imgUploadVO.setUrl(url);
        return BaseRespVo.ok(imgUploadVO);
    }

    @ExceptionHandler(AuthorizationException.class)
    public BaseRespVo authorizationException(AuthorizationException exception) {
        return BaseRespVo.fail("没有权限");
    }

    @ExceptionHandler(NumberFormatException.class)
    public BaseRespVo numberFormatException() {
        return BaseRespVo.fail("参数格式不对，请重新输入");
    }
}
