package com.example.applicant_web;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ApplicantMapper {

    // 全件を名前順で取得
    List<Applicant> findAllOrderByName();

    // 名前の部分一致で検索(名前順)
    List<Applicant> findByNameContaining(@Param("keyword") String keyword);

    // idで1件取得(なければnull)
    Applicant findById(@Param("id") Long id);

    // 追加
    void insert(Applicant applicant);

    // 更新
    void update(Applicant applicant);

    // 削除
    void deleteById(@Param("id") Long id);
}