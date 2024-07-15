package com.yusuf.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    // expose "/" that return "Hello World"

    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    // expose a new endpoint for "workout"
    @GetMapping ("/workout")
        public String workout() {
        return "yusuf baba 49";
    }

    // expose a new endpoint for "x"
    @GetMapping("/x")
    public String x() {
        return "mush";
    }

    // inject properties
    @Value("${team.name}")
    private String coachName;

    @Value("${person.name}")
    private String personName;

    // expose new endpoints for names
    @GetMapping("/names")
    public String names() {
        return personName + " - " + coachName;

    }

}
