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

    // setter injection
    @Autowired
    public void xMethod(Instructor member) {
        this.currentMember = member;
    }

    @GetMapping("example")
    public String example() {
        return "Example endpoint working... ";
    }

    @GetMapping("/memberType")
    public String memberType() {
        return currentMember.getTypeOfJob();
    }
}
