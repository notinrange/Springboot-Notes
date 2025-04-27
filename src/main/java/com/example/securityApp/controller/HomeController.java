package com.example.securityApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(HttpServletRequest request){
        System.out.println(request.getSession().getId());
        return "home";
    }
}
