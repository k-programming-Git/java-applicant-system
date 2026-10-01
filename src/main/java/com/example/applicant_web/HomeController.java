package com.example.applicant_web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    // DBの処理は、Service経由で行う
    private final ApplicantService service;

    public HomeController(ApplicantService service) {
        this.service = service;
    }

    // 一覧表示・検索
    @GetMapping("/")
    public String home(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String message,
            Model model) {

        model.addAttribute("applicants", service.search(keyword));
        model.addAttribute("message", message);
        return "home";
    }

    // 追加
    @GetMapping("/add")
    public String addApplicant(@RequestParam String name) {
        if (name.isBlank()) {
            return "redirect:/?message=Please+input+name";
        }
        service.add(name);
        return "redirect:/";
    }

    // 削除
    @GetMapping("/delete")
    public String deleteApplicant(@RequestParam Long id) {
        service.delete(id);
        return "redirect:/";
    }

    // 編集画面を表示
    @GetMapping("/edit")
    public String editPage(@RequestParam Long id, Model model) {
        model.addAttribute("applicant", service.findById(id));
        return "edit";
    }

    // 更新
    @GetMapping("/update")
    public String updateApplicant(
            @RequestParam Long id,
            @RequestParam String name) {
        service.update(id, name);
        return "redirect:/";
    }
}