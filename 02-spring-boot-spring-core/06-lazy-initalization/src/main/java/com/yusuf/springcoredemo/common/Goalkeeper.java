package com.yusuf.springcoredemo.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
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
