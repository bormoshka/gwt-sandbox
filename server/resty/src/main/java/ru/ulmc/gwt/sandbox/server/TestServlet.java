package ru.ulmc.gwt.sandbox.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TestServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(TestServlet.class);

    private String message;

    public void init() throws ServletException {
        message = "Hello World";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Hit servlet doGet");
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        out.println("{'ping':'yes'}");
    }

    public void destroy() {

    }
}
