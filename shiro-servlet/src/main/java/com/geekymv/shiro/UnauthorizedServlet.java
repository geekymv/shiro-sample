package com.geekymv.shiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "unauthorized", urlPatterns = "/unauthorized")
public class UnauthorizedServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(UnauthorizedServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("unauthorized...");
        resp.setContentType("application/json");
        resp.getWriter().println("{\"result\":\"unauthorized\"}");
    }
}
