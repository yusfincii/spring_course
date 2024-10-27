package com.yusuf.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class Striker implements Player{

    public Striker(){
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getPosition() {
        return "Striker";
    }
}
