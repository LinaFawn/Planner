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

    @Override
    public void init() throws ServletException {
        this.excel = new Excel();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=SampleExcel.xls");
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