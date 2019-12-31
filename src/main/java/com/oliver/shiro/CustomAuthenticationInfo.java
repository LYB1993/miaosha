package com.oliver.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

/**
 * com.oliver.shiro.CustomAuthenticationInfo
 *
 * @author oliver
 * @date 2019/12/30 19:22
 */
public class CustomAuthenticationInfo implements AuthenticationInfo {

    private PrincipalCollection principals;

    private Object credentials;

    public CustomAuthenticationInfo(Object principal, Object credentials, String realmName) {
        this.principals = new SimplePrincipalCollection(principal, realmName);
        this.credentials = credentials;
    }


    @Override
    public PrincipalCollection getPrincipals() {
        return principals;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }
}
