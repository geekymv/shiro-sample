package com.geekymv.shiro;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="logout", urlPatterns = "/logoutSuccess")
public class LogoutServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SecurityUtils.getSubject().logout();
        logger.info("logout...");
        resp.setContentType("application/json");
        resp.getWriter().println("{\"result\":\"logout success\"}");
    }
}
