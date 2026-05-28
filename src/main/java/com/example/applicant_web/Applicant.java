package com.example.applicant_web;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
//@EntityでDBが保存可能になる

public class Applicant {
//Applicantを普通のJavaクラスからDBに保存できるクラスへ変更している↓
    @Id
    //DB番号が振れるようになる
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    public Applicant() {
    }
    //DB保存では、空のコンストラクタも必要なので作った↑

    public Applicant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
    return id;
}

}
