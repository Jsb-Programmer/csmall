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

        //-------
        //第一个参数Principal：就是主角信息，在后面授权的时候可以获得这个值，Session中的principal信息
        //                   你传入一个什么样的principal信息，认证成功之后就能够获得什么样的principal
        //第二个参数credentials：会和token中的password做比对
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

        PermissionExample permissionExample = new PermissionExample();
        PermissionExample.Criteria criteria = permissionExample.createCriteria();
        criteria.andRoleIdEqualTo(i);
        List<Permission> items = permissionMapper.selectByExample(permissionExample);

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
