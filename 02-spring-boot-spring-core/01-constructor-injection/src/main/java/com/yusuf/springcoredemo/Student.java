package com.yusuf.springcoredemo;

import org.springframework.stereotype.Component;

@Component
public class Student implements Member {

    @Override
    public String getTypeOfJob() {
        return "Type: Student";
    }

}