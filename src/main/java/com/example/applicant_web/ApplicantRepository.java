package com.example.applicant_web;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicantRepository
//ApplicantRepositoryという、DBを操作する担当作ります
extends JpaRepository<Applicant, Long> {
//でも中身はSpringさんが作ってね
//Springが作ってくれるので、
//HomeController.javaにある、repository.save();などのメソッドは
//私が定義していないのに、私はそのメソッドを呼び出しできる
// JpaRepositoryが親インターフェイスで、子はApplicantRepository
// JpaRepositoryが有してる、repository.save();などのメソッドの能力を、子のApplicant.repositoryが受け継いだから、repository.save()は定義なしで呼び出せる。

//ここで「このRepositoryはApplicantを扱います」と宣言しているため、Repositoryは引数にapplicantを受け取らなければいけないStringを受け取ってはいけない
//このRepositoryはApplicantを扱います」と宣言しているのはこの部分　JpaRepository<Applicant, Long>
  
List<Applicant> findAllByOrderByNameAsc();
//FindAllで全部取得して名前順にする

List<Applicant> findByNameContainingOrderByNameAsc(String Keyword);
//引数の中の名前を検索して名前順にする
}
