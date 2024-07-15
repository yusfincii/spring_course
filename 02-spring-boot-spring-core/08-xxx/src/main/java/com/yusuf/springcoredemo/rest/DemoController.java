package com.yusuf.springcoredemo.rest;

import com.yusuf.springcoredemo.common.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Player defender1;
    private Player defender2;

    private Player mid1;
    private Player mid2;

    // constructor injection
    @Autowired
    public DemoController(@Qualifier("defender") Player player1,
                          @Qualifier("defender") Player player2,
                          @Qualifier("midfielder") Player player3,
                          @Qualifier("midfielder") Player player4){
        defender1 = player1;
        defender2 = player2;

        mid1 = player3;
        mid2 = player4;

        // it will return true because defender class is singleton as default
        System.out.println(defender1 == defender2);
        // objects which belong singleton scope class they will refer same object


        // it will return false cause by midfielder classes scope is prototype
        System.out.println(mid1 == mid2);
        // objects which belong prototype scope class like mid1 and mid2 they will NOT refer the same object
        // it creates different object for each call in prototype scope

    }

    // endpoint for displaying the position of current player
    @GetMapping("/playerPosition")
    public String getPlayerPosition(){
        return defender1.getPosition();
    }
}
