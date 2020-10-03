package com.toan.ecommercedemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {

    @GetMapping("/")
    public String root() {
        return "redirect:/swagger-ui.html";
    }

    @GetMapping("/home")
    public String home() {
        return "store/index";
    }

    @GetMapping("/login")
    public String login() {
        return "store/login";
    }

    @GetMapping("/admin/crawler")
    public String crawler() {
        return "crawler";
    }
}