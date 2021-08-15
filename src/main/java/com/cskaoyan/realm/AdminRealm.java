package com.cskaoyan.realm;

import com.cskaoyan.bean.pojo.Admin;
import com.cskaoyan.bean.pojo.Permission;
import com.cskaoyan.bean.pojo.PermissionExample;
import com.cskaoyan.mapper.AdminMapper;
import com.cskaoyan.mapper.PermissionMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//先认证后授权
@Component
public class AdminRealm extends AuthorizingRealm {
    /**
     * @param authenticationToken subject执行login的时候传入的token
     */
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String credentials = null; //mi ma
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername(); //account
        //-------
        int i = adminMapper.selectIdByUsername(username);
        Admin admin = adminMapper.selectByPrimaryKey(i);

        if(username.equals(admin.getUsername())){
            credentials = admin.getPassword();
        }

        return new SimpleAuthenticationInfo(username,credentials,this.getName());
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //授权 → 提供权限
        //获得principal信息
        //获得doGetAuthen方法（上面这个方法），构造的authenticationInfo中的principal信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        //根据principal信息可以查询对应的权限信息
        int i = adminMapper.selectIdByUsername(primaryPrincipal);
        Admin admin = adminMapper.selectByPrimaryKey(i);
        Integer[] roleIds = admin.getRoleIds();
        PermissionExample permissionExample = new PermissionExample();
        List<Permission> items = new ArrayList<>();

        for (Integer roleId : roleIds) {
            PermissionExample.Criteria criteria = permissionExample.createCriteria();
            criteria.andRoleIdEqualTo(roleId);
            items.addAll(permissionMapper.selectByExample(permissionExample));
        }


        ArrayList<String> permissions = new ArrayList<>();
        for (Permission item : items) {
            if("*".equals(item.getPermission())){
                permissions = getAllPermissions();
                break;
            }
            permissions.add(item.getPermission());
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);//提供权限 👉 找到所有的钥匙

        return authorizationInfo;
    }

    public ArrayList<String> getAllPermissions(){
        List<Permission> permissions = permissionMapper.selectByExample(new PermissionExample());
        ArrayList<String> strings = new ArrayList<>();
        for (Permission permission : permissions) {
            strings.add(permission.getPermission());
        }
        return strings;
    }

}
