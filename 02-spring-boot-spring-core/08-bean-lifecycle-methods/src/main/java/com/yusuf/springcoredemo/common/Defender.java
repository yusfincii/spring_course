package com.yusuf.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class Defender implements Player{

    public Defender(){
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    // init method
    @PostConstruct
    public void preMethod(){
        System.out.println("Pre Method: " + getClass().getSimpleName());
    }

    // destroy method
    @PreDestroy
    public void postMethod(){
        System.out.println("Post Method: " + getClass().getSimpleName());
    }

    @Override
    public String getPosition() {
        return "Defender";
    }
}
