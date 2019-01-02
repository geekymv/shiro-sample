package com.geekymv.shiro;

import org.apache.shiro.SecurityUtils;
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
import java.io.Serializable;
import java.util.Date;

@WebServlet(name = "main", urlPatterns = "/main")
public class MainServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(MainServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("main...");
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Serializable sessionId = session.getId();
        String host = session.getHost();
        Date lastAccessTime = session.getLastAccessTime();
        logger.info("sessionId = " + sessionId);
        logger.info("host = " + host);
        logger.info("lastAccessTime = " + lastAccessTime);


    }
}
