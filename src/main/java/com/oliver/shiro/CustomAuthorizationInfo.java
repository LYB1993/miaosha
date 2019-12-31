package com.oliver.shiro;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;

import java.util.Collection;
import java.util.Set;

/**
 * com.oliver.shiro.CustomAuthorizationInfo
 *
 * @author oliver
 * @date 2019/12/30 20:03
 */
public class CustomAuthorizationInfo implements AuthorizationInfo {

    private Set<String> roles;

    private Set<String> permissions;

    private Set<Permission> objectPermissions;

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public void setObjectPermissions(Set<Permission> objectPermissions) {
        this.objectPermissions = objectPermissions;
    }

    @Override
    public Collection<String> getRoles() {
        return roles;
    }

    @Override
    public Collection<String> getStringPermissions() {
        return permissions;
    }

    @Override
    public Collection<Permission> getObjectPermissions() {
        return objectPermissions;
    }
}
