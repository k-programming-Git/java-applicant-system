package com.example.applicant_web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.dao.DuplicateKeyException;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // E_002 社員検索(GET /employees)
    @GetMapping
    public String list(
            @RequestParam(required = false) String employeeCd,
            @RequestParam(required = false) String employeeName,
            Model model) {

        model.addAttribute("employeeCd", employeeCd);
        model.addAttribute("employeeName", employeeName);

        model.addAttribute(
                "employees",
                service.findAll(employeeCd, employeeName)
        );

        return "employee-list";
    }

    // E_003 社員登録画面表示(GET /employees/new)
    @GetMapping("/new")
    public String newEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    // E_004 社員登録(POST /employees)
    @PostMapping
    public String create(
            @Valid Employee employee,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "employee-form";
        }

        try {
            service.create(employee);
        } catch (DuplicateKeyException e) {
            model.addAttribute(
                    "duplicateError",
                    "この社員コードは既に登録されています。"
            );
            return "employee-form";
        }

        return "redirect:/employees";
    }

    // E_005 社員詳細(GET /employees/{id})
    @GetMapping("/{id}")
    public String detail(
            @PathVariable Long id,
            Model model) {

        Employee employee = service.findById(id);

        if (employee == null) {
            model.addAttribute("message", "社員が見つかりません。");
            return "error";
        }

        model.addAttribute("employee", employee);
        return "employee-detail";
    }

    // E_006 社員編集画面(GET /employees/{id}/edit)
    @GetMapping("/{id}/edit")
    public String edit(
            @PathVariable Long id,
            Model model) {

        Employee employee = service.findById(id);

        if (employee == null) {
            model.addAttribute("message", "社員が見つかりません。");
            return "error";
        }

        model.addAttribute("employee", employee);
        return "employee-edit";
    }

    // E_007 社員更新(POST /employees/{id})
    @PostMapping("/{id}")
    public String update(
            @PathVariable Long id,
            @Valid Employee employee,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "employee-edit";
        }

        Employee existingEmployee = service.findById(id);

        if (existingEmployee == null) {
            model.addAttribute("message", "社員が見つかりません。");
            return "error";
        }

        employee.setId(id);
        service.update(employee);

        return "redirect:/employees/" + id;
    }

    // E_008 社員論理削除(POST /employees/{id}/delete)
    @PostMapping("/{id}/delete")
    public String delete(
            @PathVariable Long id,
            Model model) {

        Employee employee = service.findById(id);

        if (employee == null) {
            model.addAttribute("message", "社員が見つかりません。");
            return "error";
        }

        service.delete(id);

        return "redirect:/employees";
    }
}