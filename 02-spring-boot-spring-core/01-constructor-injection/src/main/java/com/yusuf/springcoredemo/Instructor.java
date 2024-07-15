package com.yusuf.springcoredemo;

import org.springframework.stereotype.Component;

@Component
public class Instructor implements Member{

    @Override
    public String getTypeOfJob() {
        return "Type: Instructor";
    }
}
