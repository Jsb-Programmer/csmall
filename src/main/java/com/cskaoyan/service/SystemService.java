package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.bo.system.RoleOptions;
import com.cskaoyan.bean.pojo.Role;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SystemService {

    BaseRespData adminList(String username, BaseParam param);

    BaseRespData logList(String name, BaseParam param);

    BaseRespData roleList(String name, BaseParam param);

    BaseRespData storageList(String name, String key, BaseParam param);

    Role roleCreate(String name, String desc);

    void roleDelete(Integer id);

    void roleUpdate(Role role);

    List<RoleOptions> roleOptions();

}
