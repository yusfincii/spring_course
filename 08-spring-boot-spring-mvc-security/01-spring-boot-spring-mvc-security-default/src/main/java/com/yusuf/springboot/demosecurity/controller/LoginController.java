package com.yusuf.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/loginPage")
    public String showMyLoginPage(){
        // return "plain-login";
        return "fancy-login";
    }

    // request mapping for access denied case
    @GetMapping("/access-denied")
    public String showAccessDenied(){
        return "access-denied";
    }
}
