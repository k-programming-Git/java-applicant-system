package com.example.applicant_web;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeMapper mapper;

    public EmployeeService(EmployeeMapper mapper) {
        this.mapper = mapper;
    }

    // E_002 社員検索
    public List<Employee> findAll(String employeeCd, String employeeName) {
        return mapper.findAll(employeeCd, employeeName);
    }

    // E_004 社員登録
    public void create(Employee employee) {
        mapper.insert(employee);
    }

    // E_005 社員詳細
    public Employee findById(Long id) {
        return mapper.findById(id);
    }
    
    // E_007 社員更新
    public void update(Employee employee) {
        mapper.update(employee);
    }

    // E_008 社員論理削除
    public void delete(Long id) {
        mapper.delete(id);
    }
}
