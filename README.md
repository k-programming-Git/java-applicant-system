# Applicant Management System

Spring Bootで作成した応募者管理システムです。

## 機能

* 応募者追加
* 応募者一覧表示
* 名前検索
* 名前順ソート
* 応募者編集
* 応募者削除

## 使用技術

* Java
* Spring Boot
* Spring Data JPA
* Thymeleaf
* H2 Database
* HTML / CSS

## 実行方法

bash
./mvnw spring-boot:run


ブラウザから以下にアクセス

text
http://localhost:8080


## 説明

Spring Data JPAを使用してデータベース操作を実装し、Thymeleafで画面表示を行っています。
応募者の追加・編集・削除・検索をWeb画面から操作できます。
