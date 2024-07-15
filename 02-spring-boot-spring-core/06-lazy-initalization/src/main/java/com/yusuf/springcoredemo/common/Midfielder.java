package com.yusuf.springcoredemo.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class Midfielder implements Player{

    public Midfielder(){
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getPosition() {
        return "Midfielder";
    }
}
