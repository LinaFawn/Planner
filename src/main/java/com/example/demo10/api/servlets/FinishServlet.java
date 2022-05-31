package com.example.demo10.api.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

public class FinishServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        final HttpSession session = req.getSession();


        String login = (String) session.getAttribute("login");
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        try {
            writer.println("<h2>Id:" + login + "</h2>");
        } finally {
            writer.close();
        }

        session.removeAttribute("password");
        session.removeAttribute("login");
        session.removeAttribute("role");

        resp.sendRedirect("/login");
    }
}
