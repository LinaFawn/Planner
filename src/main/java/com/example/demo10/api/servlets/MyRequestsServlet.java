package com.example.demo10.api.servlets;

import com.example.demo10.database.model.Requests;
import com.example.demo10.database.model.Users;
import com.example.demo10.server.DefaultService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "MyRequestsServlet", value = "/updatemy")
public class MyRequestsServlet extends HttpServlet {
    private DefaultService defaultService;

    @Override
    public void init() throws ServletException {
        this.defaultService = (DefaultService) getServletContext().getAttribute("service");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        request.setCharacterEncoding("UTF-8");
        List<Requests> requestsList = defaultService.getRequestsByLogin(user.getId());

        String jsonTask = new ObjectMapper().writeValueAsString(requestsList);


        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        out.write(jsonTask);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String link = request.getParameter("link");
        String company = request.getParameter("company");
        String training_area = request.getParameter("training_area");
        String type = request.getParameter("type");
        String reason = request.getParameter("reason");
        String format = request.getParameter("format");
        Date start_date = Date.valueOf(request.getParameter("start_date"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        int price = Integer.parseInt(request.getParameter("price"));
        String link_review = request.getParameter("link_review");
        String comment = request.getParameter("comment");

        Requests req = new Requests(0, name, link, company, training_area, type, reason, format, start_date, duration, price, link_review, comment, "created");

        System.out.println(req);

        defaultService.insertRequest(req);

        response.sendRedirect("/admin");

    }
}
