package com.example.demo10.server;

import com.example.demo10.database.dao.RequestDao;
import com.example.demo10.database.model.Requests;
import com.example.demo10.database.model.Statistics;
import com.example.demo10.database.model.Users;
import com.example.demo10.server.DefaultLogicService;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

class DefaultLogicServiceTest{
    DefaultLogicService defaultLogicService;

    @Test
    public void isLoginTest(){
        RequestDao requestDao = new RequestDao();
        List<Users> users = requestDao.selectAllUsers();
        System.out.println(users);
        defaultLogicService = new DefaultLogicService(requestDao);

        boolean flag = defaultLogicService.isLoginPresent("ABRA");
        System.out.println(flag);
    }

    @Test
    public void insertTest(){
        RequestDao requestDao = new RequestDao();
        List<Users> users = requestDao.selectAllUsers();
        System.out.println(users);
        defaultLogicService = new DefaultLogicService(requestDao);

        boolean flag = defaultLogicService.isLoginPresent("ABRA");
        System.out.println(flag);
    }


    @Test
    void isUserAuthenticatedTest() {
        RequestDao requestDao = new RequestDao();
        List<Users> users = requestDao.selectAllUsers();
        System.out.println(users);
        defaultLogicService = new DefaultLogicService(requestDao);

        boolean flag = defaultLogicService.isUserAuthenticated("kvy", "test");
        System.out.println(flag);
    }

    @Test
    void isGetRequests() {
        RequestDao requestDao = new RequestDao();
        defaultLogicService = new DefaultLogicService(requestDao);
        List<Requests> requestsList = defaultLogicService.getAllRequests();
        System.out.println(requestsList);
    }

    @Test
    void isGetRequestsStatistic() {
        RequestDao requestDao = new RequestDao();
        defaultLogicService = new DefaultLogicService(requestDao);
        List<Statistics> employeeStatistics = defaultLogicService.getCoursesStatistics(Date.valueOf("2015-03-31"), Date.valueOf("2015-03-31"));
    }

    @Test
    void isGetGrouprequestsTest() {
        RequestDao requestDao = new RequestDao();
        defaultLogicService = new DefaultLogicService(requestDao);
        Users user = defaultLogicService.getUserByRequestId(1);
        List<Requests> employeeStatistics = defaultLogicService.getAllowRequests(user);
    }
}