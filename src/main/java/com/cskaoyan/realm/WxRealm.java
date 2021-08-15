package com.cskaoyan.realm;

import com.cskaoyan.bean.pojo.User;
import com.cskaoyan.bean.pojo.UserExample;
import com.cskaoyan.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-14 15:50
 **/

@Component
public class WxRealm extends AuthorizingRealm {
    @Autowired
    UserMapper userMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String credentials = null;
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if(username.equals(users.get(0).getUsername())){
            credentials = users.get(0).getPassword();
        }
        //存入id ， password 与token中比对认证
        return new SimpleAuthenticationInfo(users.get(0).getId(),credentials,this.getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        return new SimpleAuthorizationInfo();
    }
}
