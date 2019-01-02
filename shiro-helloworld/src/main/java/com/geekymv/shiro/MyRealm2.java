package com.geekymv.shiro;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MyRealm2 extends AuthorizingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        // 根据用户名查询
        String password = "+n/oAszrl3P36ZQhYwakEA==";
        String salt = username + "CTeEL31tn94CyBzmfcGnIg==";

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password.toCharArray(), getName());

        if (salt != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(salt));
        }

        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)getAvailablePrincipal(principals);

        Set<String> roleNames = this.getRoleNamesForUser(username);
        Set<String> permissions = this.getPermissions(username, roleNames);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(permissions);

        return info;
    }

    private Set<String> getRoleNamesForUser(String username) {
        Set<String> roleNames = new HashSet<>();
        roleNames.add("admin");
        return roleNames;
    }

    private Set<String> getPermissions(String username, Collection<String> roleNames) {
        Set<String> permissions = new HashSet<>();
        permissions.add("user:create");

        return permissions;
    }

}
