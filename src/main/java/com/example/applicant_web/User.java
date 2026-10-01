package com.example.applicant_web;

// IDコードとユーザー名(usersテーブルの1行に対応するクラス)
public class User {

    private Long id;
    private String userCd;
    private String userName;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCd() {
        return userCd;
    }

    public void setUserCd(String userCd) {
        this.userCd = userCd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}