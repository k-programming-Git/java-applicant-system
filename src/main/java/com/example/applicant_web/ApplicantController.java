package com.example.applicant_web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/applicants")
public class ApplicantController {

    // DBの処理は、Service経由で行う
    private final ApplicantService service;

    public ApplicantController(ApplicantService service) {
        this.service = service;
    }

    // 一覧表示・検索(GET /applicants)
    @GetMapping
    public String list(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String message,
            Model model) {

        model.addAttribute("applicants", service.search(keyword));
        model.addAttribute("message", message);
        return "applicant-list";
    }

    // 追加(GET /applicants/add)
    @GetMapping("/add")
    public String addApplicant(@RequestParam String name) {
        if (name.isBlank()) {
            return "redirect:/applicants?message=Please+input+name";
        }
        service.add(name);
        return "redirect:/applicants";
    }

    // 削除(GET /applicants/delete)
    @GetMapping("/delete")
    public String deleteApplicant(@RequestParam Long id) {
        service.delete(id);
        return "redirect:/applicants";
    }

    // 編集画面を表示(GET /applicants/edit)
    @GetMapping("/edit")
    public String editPage(@RequestParam Long id, Model model) {
        model.addAttribute("applicant", service.findById(id));
        return "applicant-edit";
    }

    // 更新(GET /applicants/update)
    @GetMapping("/update")
    public String updateApplicant(
            @RequestParam Long id,
            @RequestParam String name) {
        service.update(id, name);
        return "redirect:/applicants";
    }
}