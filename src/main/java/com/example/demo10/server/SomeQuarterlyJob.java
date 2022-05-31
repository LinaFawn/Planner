package com.example.demo10.server;


import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

public class SomeQuarterlyJob implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(SomeQuarterlyJob.class);

    @Override
    public void run() {

        System.out.println("HI");

    }
}