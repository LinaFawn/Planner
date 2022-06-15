package com.example.demo10.api.servlets;

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
        final String start_date = request.getParameter("start_date");
        final String end_date = request.getParameter("end_date");

        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=YearReport.xls");
            HSSFWorkbook workbook = excel.docs();
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            throw new ServletException("Exception in DownLoad Excel Servlet", e);
        }
    }


    public void destroy() {
        // do nothing.
    }
}