package com.example.applicant_web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    // ホーム画面(GET /)
    @GetMapping("/")
    public String menu() {
        return "menu";
    }
}