package com.example.demo10.api.servlets;

import com.example.demo10.database.model.Requests;
import com.example.demo10.database.model.Statistics;
import com.example.demo10.server.DefaultService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "StatisticsServlet", value = "/statistics")
public class StatisticsServlet extends HttpServlet {
    private DefaultService defaultService;

    @Override
    public void init() throws ServletException {
        this.defaultService = (DefaultService) getServletContext().getAttribute("service");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final Date start_date = Date.valueOf(request.getParameter("start_date"));
        final Date last_date = Date.valueOf(request.getParameter("last_date"));
        final int number = Integer.parseInt(request.getParameter("number"));
        if(number == 1){
            List<Statistics> statistics = defaultService.getEmployeeStatistics(start_date, last_date);

            String jsonTask = new ObjectMapper().writeValueAsString(statistics);
            response.setContentType("application/json; charset=UTF-8");
            response.setStatus(200);
            PrintWriter out = response.getWriter();
            out.write(jsonTask);
        } else if(number == 2){
            List<Statistics> statistics = defaultService.getCoursesStatistics(start_date, last_date);

            String jsonTask = new ObjectMapper().writeValueAsString(statistics);
            response.setContentType("application/json; charset=UTF-8");
            response.setStatus(200);
            PrintWriter out = response.getWriter();
            out.write(jsonTask);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
