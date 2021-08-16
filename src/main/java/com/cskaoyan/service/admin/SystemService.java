package com.cskaoyan.service.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.bo.system.RoleOptions;
import com.cskaoyan.bean.pojo.Admin;
import com.cskaoyan.bean.pojo.Role;
import com.cskaoyan.bean.pojo.Storage;

import java.util.List;

public interface SystemService {

    BaseRespData adminList(String username, BaseParam param);

    BaseRespData logList(String name, BaseParam param);

    BaseRespData roleList(String name, BaseParam param);

    BaseRespData storageList(String name, String key, BaseParam param);

    Role roleCreate(String name, String desc);

    void roleDelete(Role id);

    void roleUpdate(Role role);

    List<RoleOptions> roleOptions();

    Admin adminCreate(Admin admin);

    Admin adminUpdate(Admin admin);

    Integer adminDelete(Admin admin);

    int storageUpdate(Storage storage);

    int storageDelete(Storage storage);
}
