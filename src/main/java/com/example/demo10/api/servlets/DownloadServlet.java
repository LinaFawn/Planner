package com.example.demo10.api.servlets;

import com.example.demo10.database.model.Statistics;
import com.example.demo10.database.model.Users;
import com.example.demo10.server.DefaultService;
import com.example.demo10.server.Excel;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "DownloadServlet", value = "/download")
public class DownloadServlet extends HttpServlet {
    private Excel excel;
    private DefaultService defaultService;

    @Override
    public void init() throws ServletException {
        this.defaultService = (DefaultService) getServletContext().getAttribute("service");
        this.excel = new Excel();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/download.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        String role = user.getRole();

        final Date start_date = Date.valueOf(request.getParameter("start_date"));
        final Date end_date = Date.valueOf(request.getParameter("end_date"));

        List<Statistics> coursesStatistics = defaultService.getCoursesStatistics(start_date, end_date);

        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=YearReport.xls");
            HSSFWorkbook workbook = excel.docs(coursesStatistics);
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            throw new ServletException("Exception in DownLoad Excel Servlet", e);
        }

    }


    public void destroy() {
        // do nothing.
    }
}