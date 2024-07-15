package com.yusuf.springcoredemo.rest;

import com.yusuf.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Coach currentCoach;

    // constructor injection
    @Autowired
    public DemoController(@Qualifier("tennisCoach") Coach theCoach) {
        this.currentCoach = theCoach;
    }

    /* NOTE
    * When @primary and @qualifier used together
    * class which have "qualifier" annotation will run
    * */

    @GetMapping("example")
    public String example() {
        return "Example endpoint working... ";
    }

    @GetMapping("/coachType")
    public String coachType() {
        return currentCoach.getDailyWorkout();
    }
}
