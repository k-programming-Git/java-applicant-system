package com.example.applicant_web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // 一覧表示(GET /users)
    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", service.findAll());
        return "user-list";
    }

    // 登録フォームを表示(GET /users/new)
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    // 登録する(POST /users)
    @PostMapping
    public String create(@ModelAttribute User user, Model model) {
        try {
            service.add(user);
        } catch (IllegalArgumentException e) {
            // 入力に問題があったので、入力した値を残したまま、フォームをもう一度表示する
            model.addAttribute("message", e.getMessage());
            return "user-form";
        }
        return "redirect:/users";
    }
}