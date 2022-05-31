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
import java.util.List;

@WebServlet(name = "LookingRequestsServlet", value = "/processing")
public class LookingRequestsServlet extends HttpServlet {
    private DefaultService defaultService;

    @Override
    public void init() throws ServletException {
        this.defaultService = (DefaultService) getServletContext().getAttribute("service");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        String role = user.getRole();
        List<Requests> requestsList = defaultService.getAllowRequests(user);

        String jsonTask = new ObjectMapper().writeValueAsString(requestsList);

        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        out.write(jsonTask);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
