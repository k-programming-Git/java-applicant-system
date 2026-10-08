package com.example.applicant_web;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeeMapper {

    // E_002 社員検索
    List<Employee> findAll(
            @Param("employeeCd") String employeeCd,
            @Param("employeeName") String employeeName
    );

    // E_004 社員登録
    void insert(Employee employee);

    // E_005 社員詳細
    Employee findById(@Param("id") Long id);

    // E_007 社員更新
    void update(Employee employee);
    
    // E_008 社員論理削除
    void delete(@Param("id") Long id);
}