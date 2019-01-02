package com.geekymv.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.ByteSource;

public class MyRealm implements Realm {

    @Override
    public String getName() {
        // 返回一个唯一的 realm 名字
        return this.getClass().getName();
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        // 仅支持 UsernamePasswordToken 类型的token
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 身份验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 身份
        String username = (String)token.getPrincipal();
        // 凭证
        String password= new String((char[])token.getCredentials());
        // 对用户输入的密码加密

        String salt = username;

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(salt));
        return authenticationInfo;
    }
}
