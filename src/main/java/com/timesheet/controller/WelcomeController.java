package com.timesheet.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class WelcomeController {

    @RequestMapping(path = "/welcome")
    public String getWelcomeMessage(){
        return "Welcome to Time sheet application";
    }
}
