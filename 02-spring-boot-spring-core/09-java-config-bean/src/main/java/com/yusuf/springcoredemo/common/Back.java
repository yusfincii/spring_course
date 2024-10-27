package com.yusuf.springcoredemo.common;

public class Back implements Player{

    public Back(){
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getPosition() {
        return "Back";
    }
}
