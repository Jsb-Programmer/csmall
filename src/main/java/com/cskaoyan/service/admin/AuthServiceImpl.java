package com.cskaoyan.service.admin;

import com.cskaoyan.bean.InfoData;
import com.cskaoyan.bean.pojo.*;
import com.cskaoyan.bean.vo.dashbord.AllKindsTotals;
import com.cskaoyan.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        infoData.setPerms(perms);
        infoData.setRoles(roleNames);

        return infoData;
    }
}
