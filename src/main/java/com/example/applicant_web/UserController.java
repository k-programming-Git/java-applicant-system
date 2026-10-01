package com.example.applicant_web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
            model.addAttribute("message", e.getMessage());
            return "user-form";
        }
        return "redirect:/users";
    }

    // 編集フォームを表示(GET /users/{id}/edit)
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("user", service.findById(id));
        } catch (IllegalArgumentException e) {
            // 存在しないidなら、一覧に戻す
            return "redirect:/users";
        }
        return "user-form";
    }

    // 更新する(POST /users/{id})
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute User user, Model model) {
        user.setId(id); // URLのidを、Userにセットする
        try {
            service.update(user);
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            return "user-form";
        }
        return "redirect:/users";
    }
}