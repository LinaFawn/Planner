package com.example.demo10.api.servlets;

import com.example.demo10.database.model.Requests;
import com.example.demo10.server.DefaultService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet(name = "CreateServlet", value = "/create")
public class CreateServlet extends HttpServlet {
    private DefaultService defaultService;

    @Override
    public void init() throws ServletException {
        this.defaultService = (DefaultService) getServletContext().getAttribute("service");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String name = request.getParameter("name");
        final String link = request.getParameter("link");
        final String company = request.getParameter("company");
        final String training_area = request.getParameter("training_area");
        final String type = request.getParameter("type");
        final String reason = request.getParameter("reason");
        final String format = request.getParameter("format");
        final Date start_date = Date.valueOf(request.getParameter("start_date"));
        final int duration = Integer.parseInt(request.getParameter("duration"));
        final int price = Integer.parseInt(request.getParameter("price"));
        final String link_review = request.getParameter("link_review");
        final String comment = request.getParameter("comment");

        Requests requests = new Requests(78, name, link, company, training_area, type, reason, format, start_date, duration, price, link_review, comment, "created");

        defaultService.insertRequest(requests);
        request.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(request, response);
/*
        String jsonTask = new ObjectMapper().writeValueAsString(update);
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        out.write(jsonTask);*/
    }
}
