package com.yusuf.springcoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Member currentMember;

    // define a constructor for dependency injection
    @Autowired
    public DemoController(Instructor currentMember) {
        this.currentMember = currentMember;
    }

    @GetMapping("/memberType")
    public String memberType() {
        return currentMember.getTypeOfJob();
    }
}
