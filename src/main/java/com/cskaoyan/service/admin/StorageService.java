package com.cskaoyan.service.admin;

import com.cskaoyan.bean.pojo.Storage;
import com.cskaoyan.bean.vo.storage.ImgUploadVO;

public interface StorageService {
    ImgUploadVO imgUpload(Storage storage);
}
