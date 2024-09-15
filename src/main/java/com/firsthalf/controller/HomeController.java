package com.firsthalf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String listHome(){
        return "home";
    }

    @GetMapping("/home")
    public String getHome(){
        return "home";
    }

}
