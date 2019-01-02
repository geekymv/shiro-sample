package com.geekymv.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final transient Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        boolean authenticated = currentUser.isAuthenticated();
        if(!authenticated) {
            UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");
            token.setRememberMe(true);

            try {
                currentUser.login(token);
            }catch (UnknownAccountException e) {
                log.error(token.getPrincipal() + " 帐号不存在");
            }catch (IncorrectCredentialsException e) {
                log.error(token.getPrincipal() + " 的密码错误");
            }catch (LockedAccountException e) {
                log.error(token.getPrincipal() + " 帐号别锁定，请联系管理员");
            }catch (AuthenticationException e) {
                log.error(token.getPrincipal() + " 认证失败");
            }

            authenticated = currentUser.isAuthenticated();
            if(!authenticated) {
                log.error("" + token.getPrincipal() + "登录失败");
                return;
            }


        }else {
            log.info("authenticated = " + authenticated);
        }

        log.info("" + currentUser.getPrincipal() + "登录成功");

        if(currentUser.hasRole("employee")) {
            log.info(currentUser + " 有employee角色");
        }else {
            log.info(currentUser + " 没有employee角色");
        }

        if(currentUser.isPermittedAll("user:create")) {
            log.info(currentUser + " 有 user:create权限");
        }else {
            log.info(currentUser + " 没有 user:create权限");
        }

        // 退出
        currentUser.logout();

    }

}
