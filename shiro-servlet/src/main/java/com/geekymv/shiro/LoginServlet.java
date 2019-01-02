package com.geekymv.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        logger.info("username = {}, password = {}", username, password);

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
        }catch (UnknownAccountException e) {
            logger.error(token.getPrincipal() + " 帐号不存在");
        }catch (IncorrectCredentialsException e) {
            logger.error(token.getPrincipal() + " 的密码错误");
        }catch (LockedAccountException e) {
            logger.error(token.getPrincipal() + " 帐号别锁定，请联系管理员");
        }catch (AuthenticationException e) {
            logger.error(token.getPrincipal() + " 认证失败");
        }

        Session session = SecurityUtils.getSubject().getSession();
        logger.info("sessionId = {}", session.getId());

        resp.setContentType("application/json");
        resp.getWriter().println("{\"result\":\"login success\", \"ACCESS_TOKEN\":'"+session.getId()+"'}");
    }
}
