package com.example.demo10.server;

import com.example.demo10.database.dao.RequestDao;
import com.example.demo10.database.model.Requests;
import com.example.demo10.database.model.Users;

import javax.servlet.ServletContext;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class SiteMapThread implements Runnable{
    private ServletContext context;
    private DefaultService defaultService;
    private RequestDao dao;
    private SendMessage sendMessage;

    public SiteMapThread(ServletContext context, RequestDao requestDao, DefaultService defaultService) {
        this.context = context;
        this.defaultService = defaultService;
        this.dao = requestDao;
    }

    @Override
    public void run() {
        //sendMessage = new SendMessage();
        checkingDate();
    }

    private void checkingDate(){
        List<Requests> allRequests = defaultService.getAllRequests();
        Date nowTime = new Date(Calendar.getInstance().getTime().getTime());
        for(Requests r: allRequests){
            Date start_date = r.getStart_date();
            Users user = defaultService.getUserByRequestId(r.getId());


            /*if (start_date.getTime() - nowTime.getTime() == 2592000000)
                sendMessage.SendMessage("NOTIFICATION! Your cours will start in 1 month!!!", user.getEmail());*/

            if (start_date.getTime() + 604800000 == nowTime.getTime())
                sendMessage.SendMessage("NOTIFICATION! Your cours will start in 1 weeks!!!", user.getEmail());

            if (start_date.getTime() + 86400000 == nowTime.getTime())
                sendMessage.SendMessage("NOTIFICATION! Your cours will start in 1 day!!!", user.getEmail());
        }
    }

}