package com.example.applicant_web;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    // 全件をIDコード順で取得
    List<User> findAll();

    // idで1件取得(なければnull)
    User findById(@Param("id") Long id);

    // 追加
    void insert(User user);

    // 更新
    void update(User user);
}