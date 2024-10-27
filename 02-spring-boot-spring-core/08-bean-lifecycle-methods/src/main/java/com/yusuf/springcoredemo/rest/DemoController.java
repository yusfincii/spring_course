package com.yusuf.springcoredemo.rest;

import com.yusuf.springcoredemo.common.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // constructor injection
    @Autowired
    public DemoController(Player player){
        //System.out.println(player.getClass().getSimpleName());

    }
}
