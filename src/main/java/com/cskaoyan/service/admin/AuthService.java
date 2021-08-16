package com.cskaoyan.service.admin;


import com.cskaoyan.bean.InfoData;
import com.cskaoyan.bean.vo.dashbord.AllKindsTotals;

public interface AuthService {
    AllKindsTotals queryTotals();

    InfoData queryAdminByName(String principal);

    void updateAdminLoginInfo(String username);

    int changeAdminPwd(String oldPassword, String newPassword) throws Exception;
}
