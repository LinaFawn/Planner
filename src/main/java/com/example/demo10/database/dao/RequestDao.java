package com.example.demo10.database.dao;

import com.example.demo10.database.model.Requests;
import com.example.demo10.database.model.Users;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDao {
    private static String url = "jdbc:mysql://localhost:3306/education_planner";
    private static String username = "root";
    private static String password = "rootroot";

    public RequestDao() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return connection;
    }

    //all users
    public List<Users> selectAllUsers() {
        List<Users> users = new ArrayList<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                String first = resultSet.getString(4);
                String second = resultSet.getString(5);
                String last = resultSet.getString(6);
                String email = resultSet.getString(7);

                Users user = new Users(id, login, password, first, second, last, email, null);
                users.add(user);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    // 1 user by login
    public Users selectUserByLogin(String login) {
        Users user = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM users WHERE login=?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, login);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        String password1 = resultSet.getString(3);
                        String first = resultSet.getString(4);
                        String second = resultSet.getString(5);
                        String last = resultSet.getString(6);
                        String email = resultSet.getString(7);

                        user = new Users(id, login, password1, first, second, last, email, null);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return user;
    }

    // 1 user by user_id
    public Users selectUserById(int userId) {
        Users user = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM users WHERE id=?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, userId);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        String login = resultSet.getString(2);
                        String password1 = resultSet.getString(3);
                        String first = resultSet.getString(4);
                        String second = resultSet.getString(5);
                        String last = resultSet.getString(6);
                        String email = resultSet.getString(7);

                        user = new Users(id, login, password1, first, second, last, email, null);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return user;
    }

    //add new request
    public int insertRequest(Requests request) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {


                String sql = "INSERT INTO requests (name, link, company,training_area,type, reason, format, start_date,duration,price,link_review) Values (?,?,?,?,?,?,?,?,?,?,?)";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, request.getName());
                    preparedStatement.setString(2, request.getLink());
                    preparedStatement.setString(3, request.getCompany());
                    preparedStatement.setString(4, request.getTraining_area());
                    preparedStatement.setString(5, request.getType());
                    preparedStatement.setString(6, request.getReason());
                    preparedStatement.setString(7, request.getFormat());
                    preparedStatement.setDate(8, request.getStart_date());
                    preparedStatement.setInt(9, request.getDuration());
                    preparedStatement.setInt(10, request.getPrice());
                    preparedStatement.setString(11, request.getLink_review());

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    // select all requests for 1 user
    public List<Requests> selectRequestsByUserId(int user_id) {
        List<Integer> request_id = new ArrayList<>();
        List<Requests> requestsList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM request_user WHERE user_id=?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, user_id);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        request_id.add(resultSet.getInt(1));
                    }
                    for (int i : request_id) {
                        requestsList.add(selectRequestById(i));
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return requestsList;
    }

    //help select request by request id
    public Requests selectRequestById(int requestId) {
        Requests requests = new Requests();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM requests WHERE id=?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, requestId);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int reqid = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        String link = resultSet.getString(3);
                        String company = resultSet.getString(4);
                        String training_area = resultSet.getString(5);
                        String type = resultSet.getString(6);
                        String reason = resultSet.getString(7);
                        String format = resultSet.getString(8);
                        Date start_date = resultSet.getDate(9);
                        int duration = resultSet.getInt(10);
                        int price = resultSet.getInt(11);
                        String link_review = resultSet.getString(12);
                        String comment = resultSet.getString(13);
                        String status = resultSet.getString(14);

                        requests = new Requests(reqid, name, link, company, training_area, type, reason, format, start_date, duration, price, link_review, comment, status);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return requests;
    }

    //select all requests
    public List<Requests> selectAllRequests() {
        List<Requests> requestsList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM requests");

            while (resultSet.next()) {
                int reqid = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String link = resultSet.getString(3);
                String company = resultSet.getString(4);
                String training_area = resultSet.getString(5);
                String type = resultSet.getString(6);
                String reason = resultSet.getString(7);
                String format = resultSet.getString(8);
                Date start_date = resultSet.getDate(9);
                int duration = resultSet.getInt(10);
                int price = resultSet.getInt(11);
                String link_review = resultSet.getString(12);
                String comment = resultSet.getString(13);
                String status = resultSet.getString(14);

                Requests req = new Requests(reqid, name, link, company, training_area, type, reason, format, start_date, duration, price, link_review, comment, status);
                requestsList.add(req);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return requestsList;
    }


    // select user role
    public String selectUserRoleByLogin(String login) {
        Users getUserId = selectUserByLogin(login);
        String str = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM user_group WHERE user_id=?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, getUserId.getId());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int user_id = resultSet.getInt(1);
                        int group_id = resultSet.getInt(2);

                        str = selectRoleByGroupId(group_id);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return str;
    }

    //help select role by login
    public String selectRoleByGroupId(int groupid) {
        String role = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM `groups` WHERE id=?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, groupid);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int grid = resultSet.getInt(1);
                        role = resultSet.getString(2);
                        int leader_id = resultSet.getInt(3);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return role;
    }

    //updateRequest
    public void updateRequest(Requests request) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "UPDATE requests SET name=?, link=?, company=?, training_area=?, type=?, reason=?, format=?, start_date=?, duration=?, price=?, link_review=?, comment=?, status=? WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, request.getName());
                preparedStatement.setString(2, request.getLink());
                preparedStatement.setString(3, request.getCompany());
                preparedStatement.setString(4, request.getTraining_area());
                preparedStatement.setString(5, request.getType());
                preparedStatement.setString(6, request.getReason());
                preparedStatement.setString(7, request.getFormat());
                preparedStatement.setDate(8, request.getStart_date());
                preparedStatement.setInt(9, request.getDuration());
                preparedStatement.setInt(10, request.getPrice());
                preparedStatement.setString(11, request.getLink_review());
                preparedStatement.setString(12, request.getComment());
                preparedStatement.setString(13, request.getStatus());

                preparedStatement.setInt(14, request.getId());
                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    // select user by request_id
    public Users selectUserByRequestId(int requestId) {
        int user_id = -10;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM request_user WHERE request_id=?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, requestId);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        user_id = resultSet.getInt(2);
                        int group_id = resultSet.getInt(1);

                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        Users user = selectUserById(user_id);

        return user;
    }

    //select all group id
    public List<Integer> selectAllLeaderId() {
        List<Integer> groupidList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `groups`");

            while (resultSet.next()) {
                int group_id = resultSet.getInt(3);

                groupidList.add(group_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return groupidList;
    }

    // select requests by date
    public List<Requests> selectRequestsByDate(Date start, Date last) {
        List<Requests> requestsList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM requests WHERE start_date<=? and start_date >=?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setDate(1, last);
                    preparedStatement.setDate(2, start);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        int reqid = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        String link = resultSet.getString(3);
                        String company = resultSet.getString(4);
                        String training_area = resultSet.getString(5);
                        String type = resultSet.getString(6);
                        String reason = resultSet.getString(7);
                        String format = resultSet.getString(8);
                        Date start_date = resultSet.getDate(9);
                        int duration = resultSet.getInt(10);
                        int price = resultSet.getInt(11);
                        String link_review = resultSet.getString(12);
                        String comment = resultSet.getString(13);
                        String status = resultSet.getString(14);

                        Requests req = new Requests(reqid, name, link, company, training_area, type, reason, format, start_date, duration, price, link_review, comment, status);
                        requestsList.add(req);
                    }
                } catch (SQLException e) {
                    printSQLException(e);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return requestsList;
    }

    //print exc
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


}
