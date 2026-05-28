package com.example.applicant_web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    ApplicantManager manager = new ApplicantManager();
    //すぐ下で応募者の情報を取得しようとしてるけど、取得するためには、manager.getApplicantがつかえるようにしなきゃいけない。
    // それを使えるようにするにはさらにmanagerが使えるようにしなきゃいけない。そのため、ApplicantManager managerでmanager変数を使えるようにした。

    @GetMapping("/")
    public String home(Model model) {
                model.addAttribute("applicants", manager.getApplicants());
                return "home";
            }

    @GetMapping("/add")
        public String addApplicant(
        @RequestParam String name) {

        manager.addApplicant(name);

        return "redirect:/";

}
}