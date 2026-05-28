package com.example.applicant_web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private ApplicantRepository repository;

    public HomeController(ApplicantRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute(
                "applicants",
                repository.findAll()
        );

        return "home";
    }

    @GetMapping("/add")
    public String addApplicant(
            @RequestParam String name
    ) {

        repository.save(
                new Applicant(name)
        );

        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteApplicant(
            @RequestParam Long id
    ) {

        repository.deleteById(id);

        return "redirect:/";
    }

}
