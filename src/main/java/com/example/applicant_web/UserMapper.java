package com.example.applicant_web;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // 全件をIDコード順で取得
    List<User> findAll();

    // 追加
    void insert(User user);
}