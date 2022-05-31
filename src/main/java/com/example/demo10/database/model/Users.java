package com.example.demo10.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder()
public class Users {
    private int id;
    private String login;
    private String pass_word;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String email;
    private String role;
}
