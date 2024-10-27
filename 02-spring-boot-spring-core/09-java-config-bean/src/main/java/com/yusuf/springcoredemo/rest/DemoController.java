package com.yusuf.springcoredemo.rest;

import com.yusuf.springcoredemo.common.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // private field for dependency
    private Player myPlayer;

    // constructor injection
    @Autowired
    public DemoController(@Qualifier("backPos") Player player){
        System.out.println(player.getClass().getSimpleName());
        myPlayer = player;
    }

    @GetMapping("/controller")
    public String controller(){
        return myPlayer.getPosition();
    }
}
