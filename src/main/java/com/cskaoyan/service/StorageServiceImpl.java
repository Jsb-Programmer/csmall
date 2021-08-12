package com.cskaoyan.service;

import com.cskaoyan.bean.pojo.Storage;
import com.cskaoyan.bean.pojo.StorageExample;
import com.cskaoyan.bean.vo.storage.ImgUploadVO;
import com.cskaoyan.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    StorageMapper storageMapper;

    @Transactional
    @Override
    public ImgUploadVO imgUpload(Storage storage) {
        int id = storageMapper.addStorage(storage);
        storage.setId(id);

        ImgUploadVO imgUploadVO = new ImgUploadVO();
        imgUploadVO.setId(storage.getId());
        imgUploadVO.setKey(storage.getKey());
        imgUploadVO.setName(storage.getName());
        imgUploadVO.setSize(storage.getSize());
        imgUploadVO.setUrl(storage.getUrl());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addTime = simpleDateFormat.format(storage.getAddTime());
        String updateTime = simpleDateFormat.format(storage.getUpdateTime());
        imgUploadVO.setUpdateTime(addTime);
        imgUploadVO.setUpdateTime(updateTime);

        return imgUploadVO;
    }
}
