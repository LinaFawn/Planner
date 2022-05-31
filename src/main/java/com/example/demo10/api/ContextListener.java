package com.example.demo10.api;

import com.example.demo10.api.servlets.dmo.Excel;
import com.example.demo10.database.dao.RequestDao;
import com.example.demo10.server.DefaultLogicService;
import com.example.demo10.server.DefaultService;
import com.example.demo10.server.SiteMapThread;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class ContextListener implements ServletContextListener {
    private DefaultService defaultService;
    private RequestDao requestDao;
    private Excel excel;
    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        requestDao = new RequestDao();
        defaultService = new DefaultLogicService(requestDao);

        scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable command = new SiteMapThread(servletContextEvent.getServletContext(), requestDao, defaultService);
        // Delay 1 Minute to first execution
        long initialDelay = 0;
        TimeUnit unit = TimeUnit.DAYS;
        // period the period between successive executions
        long period = 1;// 60 Minute!

        scheduler.scheduleAtFixedRate(command, initialDelay, period, unit);

        excel = new Excel();

        servletContext.setAttribute("service", defaultService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        scheduler.shutdownNow();
    }
}
