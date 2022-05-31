package com.example.demo10.server;

import com.example.demo10.database.dao.RequestDao;
import com.example.demo10.database.model.Groups;
import com.example.demo10.database.model.Requests;
import com.example.demo10.database.model.Statistics;
import com.example.demo10.database.model.Users;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DefaultLogicService implements DefaultService {

    private final RequestDao requestDao;

    @Override
    public boolean isLoginPresent(String login) {
        Users user = requestDao.selectUserByLogin(login);
        if (user == null)
            return false;
        return true;

    }

    @Override
    public boolean isUserAuthenticated(String login, String password) {
        Users user = requestDao.selectUserByLogin(login);
        if (user == null || !user.getPass_word().equals(password))
            return false;
        return true;
    }

    @Override
    public Users userByLogin(String login) {
        Users user = requestDao.selectUserByLogin(login);
        String role = getRole(login);
        user.setRole(role);
        return user;
    }

    @Override
    public List<Requests> getAllRequests() {
        List<Requests> requestsList = requestDao.selectAllRequests();
        return requestsList;
    }

    @Override
    public void insertRequest(Requests requests) {
        int kort = requestDao.insertRequest(requests);
    }

    @Override
    public String getRole(String login) {
        String role = requestDao.selectUserRoleByLogin(login);
        return role;
    }

    @Override
    public List<Requests> getRequestsByLogin(int user_id) {
        List<Requests> requestsList = requestDao.selectRequestsByUserId(user_id);
        return requestsList;
    }

    @Override
    public Requests updateRequest(Requests request) {
        requestDao.updateRequest(request);
        return requestDao.selectRequestById(request.getId());
    }

    @Override
    public Users getUserByRequestId(int request_id) {
        return requestDao.selectUserByRequestId(request_id);
    }

    @Override
    public Requests getRequestById(int requestId) {
        return requestDao.selectRequestById(requestId);
    }

    @Override
    public List<Statistics> getEmployeeStatistics(Date start_date, Date last_date) {
        List<Statistics> statisticsList = new ArrayList<>();
        List<Requests> requestsList = requestDao.selectRequestsByDate(start_date, last_date);
        List<Date> dates = new ArrayList<>();

        for (Requests i : requestsList) {
            dates.add(i.getStart_date());
        }

        Map<Date, Long> counts =
                dates.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        for (Map.Entry<Date, Long> entry : counts.entrySet()) {
            Statistics statistics = new Statistics(entry.getKey().toString(), entry.getValue());
            statisticsList.add(statistics);

        }
        return statisticsList;
    }

    @Override
    public List<Statistics> getCoursesStatistics(Date start_date, Date last_date) {
        List<Statistics> statisticsList = new ArrayList<>();
        List<Requests> requestsList = requestDao.selectRequestsByDate(start_date, last_date);
        List<String> courses = new ArrayList<>();

        for (Requests i : requestsList) {
            courses.add(i.getName());
        }

        Map<String, Long> counts =
                courses.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        for (Map.Entry<String, Long> entry : counts.entrySet()) {
            Statistics statistics = new Statistics(entry.getKey().toString(), entry.getValue());
            statisticsList.add(statistics);

        }
        return statisticsList;
    }

    @Override
    public List<Requests> getAllowRequests(Users user) {
        String role = user.getRole();
        List<Requests> requestsList = new ArrayList<>();
        List<Groups> groupsList = requestDao.selectAllGroups();

//        if (role.equals("education") || role.equals("leaders") || role.equals("directorate"))
            //requestsList = requestDao.selectRequestsByStatus("allow leader group");

        if(groupsList.contains(user.getId())){
            int user_id = user.getId();
            Groups temp = groupsList.stream()
                    .filter(a -> a.equals(user_id))
                    .findFirst()
                    .orElse(null);
            System.out.println(temp);
        }


        return requestsList;
    }

}
