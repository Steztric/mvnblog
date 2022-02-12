package com.mantiso;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/home", "*.do"}, initParams = {@WebInitParam(name = "ProductName", value = "Welcome Application")})
public class SimpleServlet extends HttpServlet {
    String appName;

    @Override
    public void init() throws ServletException {
        appName = getInitParameter("ProductName");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        if(name != null) {
            resp.setContentType("text/xml");
            PrintWriter writer = resp.getWriter();
            writer.write("<application>");
            writer.printf("<name>Hello %s!</name>", name);
            writer.printf("<product>%s</product>", appName);
            writer.write("</application>");
        } else {
            resp.getWriter().write("Please supply a name");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        if(name != null) {
            resp.getWriter().printf("Hello %s!", name);
        } else {
            resp.getWriter().write("Please supply a name");
        }
    }
}
