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
public class Requests {
    private int id;
    private String name;
    private String link;
    private String company;
    private String training_area;
    private String type;
    private String reason;
    private String format;
    private Date start_date;
    private int duration;
    private int price;
    private String link_review;
    private String comment;
    private String status;
}
