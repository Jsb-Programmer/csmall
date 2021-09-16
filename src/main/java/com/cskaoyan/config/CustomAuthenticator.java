package com.cskaoyan.config;

import com.cskaoyan.realm.MallToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-14 15:52
 **/

public class CustomAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        //doAuthenticate
        this.assertRealmsConfigured();
        Collection<Realm> originalRealms = this.getRealms();
        MallToken mallToken = (MallToken) authenticationToken;
        String type = mallToken.getType();
        ArrayList<Realm> realms = new ArrayList<>();
        for (Realm originalRealm : originalRealms) {
            if (originalRealm.getName().toLowerCase().contains(type)){
                realms.add(originalRealm);
            }
        }
        if (realms.size() == 1) {
            return doSingleRealmAuthentication( realms.iterator().next(), authenticationToken);
        } else {
            return doMultiRealmAuthentication(realms, authenticationToken);
        }
    }
}
