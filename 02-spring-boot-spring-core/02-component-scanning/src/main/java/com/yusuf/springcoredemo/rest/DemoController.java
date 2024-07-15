package com.yusuf.springcoredemo.rest;

import com.yusuf.springcoredemo.common.Instructor;
import com.yusuf.springcoredemo.common.Member;
import com.yusuf.springcoredemo.common.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Member currentMember;

    // define a constructor for dependency injection
    @Autowired
    public DemoController(Student currentMember) {

        this.currentMember = currentMember;
    }

    // an example endpoint
    @GetMapping("example")
    public String example() {
        return "Example endpoint working... ";
    }

    // member type
    @GetMapping("/memberType")
    public String memberType() {
        return currentMember.getTypeOfJob();
    }
}
