package com.example.demo10.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder()
public class Groups {
    private int id;
    private String name;
    private int leader_id;

}