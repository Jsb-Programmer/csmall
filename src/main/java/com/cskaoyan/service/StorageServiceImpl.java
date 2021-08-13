package com.cskaoyan.service;

import com.cskaoyan.bean.pojo.Storage;
import com.cskaoyan.bean.vo.storage.ImgUploadVO;
import com.cskaoyan.mapper.StorageMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    StorageMapper storageMapper;

    /**
     * 图片上传
     * @param storage 图片上传POJO对象
     * @return 图片上传VO对象
     */
    @Transactional
    @Override
    public ImgUploadVO imgUpload(Storage storage) {
        storageMapper.addStorage(storage);

        ImgUploadVO imgUploadVO = new ImgUploadVO();
        BeanUtils.copyProperties(storage, imgUploadVO);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addTime = simpleDateFormat.format(storage.getAddTime());
        String updateTime = simpleDateFormat.format(storage.getUpdateTime());
        imgUploadVO.setUpdateTime(addTime);
        imgUploadVO.setUpdateTime(updateTime);

        return imgUploadVO;
    }
}
