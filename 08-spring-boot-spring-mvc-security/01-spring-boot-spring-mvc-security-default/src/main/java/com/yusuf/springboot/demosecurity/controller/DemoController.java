package com.yusuf.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/home")
    public String showHome(){
        return "home";
    }

    @GetMapping("/managers")
    public String showManagers(){
        return "managers";
    }

    @GetMapping("/admins")
    public String showAdmins(){
        return "admins";
    }

}
