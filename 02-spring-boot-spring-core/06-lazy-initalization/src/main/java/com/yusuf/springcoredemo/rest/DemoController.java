package com.yusuf.springcoredemo.rest;

import com.yusuf.springcoredemo.common.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Player playerX;

    // constructor injection
    @Autowired
    public DemoController(@Qualifier("goalkeeper") Player player){
        playerX = player;
        System.out.println("In DemoController constructor: " + getClass().getSimpleName());
    }

    // endpoint for displaying the position of current player
    @GetMapping("/playerPosition")
    public String getPlayerPosition(){
        return playerX.getPosition();
    }
}
