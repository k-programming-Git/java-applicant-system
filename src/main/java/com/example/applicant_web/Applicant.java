package com.example.applicant_web;

// 応募者1人分のデータ(applicantsテーブルの1行に対応するクラス)
public class Applicant {

    private Long id;
    private String name;

    public Applicant() {
    }

    public Applicant(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}