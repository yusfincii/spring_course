package com.yusuf.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class Goalkeeper implements Player{

    public Goalkeeper(){
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getPosition() {
        return "Goalkeeper";
    }
}
