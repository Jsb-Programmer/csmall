package com.cskaoyan.service.admin;

import com.cskaoyan.bean.InfoData;
import com.cskaoyan.bean.pojo.*;
import com.cskaoyan.bean.vo.dashbord.AllKindsTotals;
import com.cskaoyan.mapper.*;
import com.cskaoyan.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-11 23:44
 **/

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    PermissionMapMapper permissionMapMapper;

    /**
     * show dashboard number
     * @return
     */
    @Override
    @Transactional
    public AllKindsTotals queryTotals() {
        long orderCount = orderMapper.countByExample(new OrderExample());
        long goodsCount = goodsMapper.countByExample(new GoodsExample());
        long userCount = userMapper.countByExample(new UserExample());
        long productCount = productMapper.countByExample(new ProductExample());
        return new AllKindsTotals(goodsCount,userCount,productCount,orderCount);
    }

    @Override
    @Transactional
    public InfoData queryAdminByName(String principal) {
        int i = adminMapper.selectIdByUsername(principal);
        Admin admin = adminMapper.selectByPrimaryKey(i);
        InfoData infoData = new InfoData();
        infoData.setAvatar(admin.getAvatar());
        infoData.setName(admin.getUsername());

        Integer[] roleIds = admin.getRoleIds();
        ArrayList<String> roleNames = new ArrayList<>();
        ArrayList<String> perms = new ArrayList<>();

        PermissionExample permissionExample = new PermissionExample();
        for (Integer roleId : roleIds) {
            PermissionExample.Criteria criteria = permissionExample.createCriteria();
            Role role = roleMapper.selectByPrimaryKey(roleId);
            roleNames.add(role.getName());
            criteria.andRoleIdEqualTo(roleId);
            List<Permission> permissions = permissionMapper.selectByExample(permissionExample);
            for (Permission permission : permissions) {
                perms.add(permission.getPermission());
            }
        }
        ArrayList<String> finalPerms = new ArrayList<>();

        for (String perm : perms) {
            String temp = permissionMapMapper.selectApiByPermission(perm);
            finalPerms.add(temp);
            if("*".equals(perm)){
                finalPerms.add(perm);
            }
        }

        infoData.setPerms(finalPerms);
        infoData.setRoles(roleNames);

        return infoData;
    }

    /**
     * 更新登录信息
     * @param username
     */
    @Override
    @Transactional
    public void updateAdminLoginInfo(String username) {
        int i = adminMapper.selectIdByUsername(username);
        Admin admin = adminMapper.selectByPrimaryKey(i);
        Subject subject = SecurityUtils.getSubject();
        String host = subject.getSession().getHost();
        admin.setLastLoginIp(host);
        admin.setLastLoginTime(new Date());
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    /**
     * 更改admin密码
     * @param oldPassword
     * @param newPassword
     */
    @Override
    public int changeAdminPwd(String oldPassword, String newPassword) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
        int i = adminMapper.selectIdByUsername(principal);
        Admin admin = adminMapper.selectByPrimaryKey(i);
        String encrypt = MD5Utils.encrypt(oldPassword);
        if (!admin.getPassword().equals(encrypt)){
            return 400;
        }
        admin.setUpdateTime(new Date());
        admin.setPassword(MD5Utils.encrypt(newPassword));
        adminMapper.updateByPrimaryKeySelective(admin);

        return 200;
    }
}
