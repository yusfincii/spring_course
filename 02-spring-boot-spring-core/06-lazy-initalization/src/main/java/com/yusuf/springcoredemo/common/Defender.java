package com.yusuf.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class Defender implements Player{

    public Defender(){
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getPosition() {
        return "Defender";
    }
}
