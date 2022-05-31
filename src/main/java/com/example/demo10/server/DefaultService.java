package com.example.demo10.server;

import com.example.demo10.database.model.Requests;
import com.example.demo10.database.model.Statistics;
import com.example.demo10.database.model.Users;

import java.sql.Date;
import java.util.List;

public interface DefaultService {
    boolean isLoginPresent(String login);
    boolean isUserAuthenticated(String login, String Password);
    Users userByLogin(String login);
    List<Requests> getAllRequests();
    void insertRequest(Requests requests);
    String getRole(String login);
    List<Requests> getRequestsByLogin(int user_id);
    Requests updateRequest(Requests request);
    Users getUserByRequestId(int request_id);
    Requests getRequestById(int requestId);
    List<Statistics> getEmployeeStatistics(Date start_date, Date last_date);
    List<Statistics> getCoursesStatistics(Date start_date, Date last_date);
//    boolean isLeader(Users user);
    List<Requests> getAllowRequests(Users user);
}
