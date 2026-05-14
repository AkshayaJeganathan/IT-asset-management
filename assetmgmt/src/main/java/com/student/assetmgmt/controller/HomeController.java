package com.student.assetmgmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Redirect root URL to asset list
    @GetMapping("/")
    public String home() {
        return "redirect:/assets";
    }
}