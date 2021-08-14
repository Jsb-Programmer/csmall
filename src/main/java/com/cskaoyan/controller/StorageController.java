package com.cskaoyan.controller;


import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.pojo.Storage;
import com.cskaoyan.bean.vo.storage.ImgUploadVO;
import com.cskaoyan.service.StorageService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.UUID;


@RestController
@RequestMapping("admin/storage")
public class StorageController {
    @Value("${file.path}")
    private String filePath;
    @Value("${img.url}")
    private String imgUrl;
    @Autowired
    StorageService storageService;

    /**
     * 图片上传
     *
     * @param file 从页面获取到的文件
     * @return
     */
    @SneakyThrows
    @PostMapping("create")
    public BaseRespVo imgUpload(MultipartFile file) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String originalFilename = file.getOriginalFilename();
        int size = (int) file.getSize();
        String contentType = file.getContentType();
        Date date = new Date(System.currentTimeMillis());
        int i = originalFilename.lastIndexOf(".");//用于截取文件尾缀
        String weizhui = originalFilename.substring(i, originalFilename.length());

        String key = uuid + weizhui;
        String url = imgUrl + key;

        Storage storage = new Storage();
        storage.setKey(key);
        storage.setName(originalFilename);
        storage.setType(contentType);
        storage.setSize(size);
        storage.setUrl(url);
        storage.setAddTime(date);
        storage.setUpdateTime(date);
        storage.setDeleted(false);


        File file1 = new File(filePath, key);
        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }

        ImgUploadVO imgUploadVO = storageService.imgUpload(storage);
        file.transferTo(file1);

        return BaseRespVo.ok(imgUploadVO);
    }
}
