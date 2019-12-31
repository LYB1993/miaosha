package com.oliver.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * com.oliver.shiro.CustomAuthenticationToken
 *
 * @author oliver
 * @date 2019/12/30 19:34
 */
public class CustomAuthenticationToken implements AuthenticationToken {


    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
