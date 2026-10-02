# 採用管理システム(Applicant System)

JavaとMySQLの学習のために作った、応募者とIDを管理するWebアプリです。
Controller → Service → Mapper(MyBatis)→ MySQL の構成で、一覧・登録・編集・削除の一連の流れを実装しています。

## 機能

### 応募者管理(`/applicants`)
- 応募者の一覧表示(名前順)
- 名前による検索(部分一致)
- 追加・編集・削除

### ID管理(`/users`)
- IDコードとユーザー名の一覧表示
- 新規登録・編集(登録と編集で同じフォームを使用)
- 入力チェック(IDコード・ユーザー名の必須チェック、IDコードの重複チェック)

### ホーム(`/`)
- 応募者管理とID管理へのメニュー画面

## 使用技術

- Java 21
- Spring Boot 4.0.6
- MyBatis(mybatis-spring-boot-starter 4.0.1)
- MySQL 8.4
- Thymeleaf
- Maven(Maven Wrapper)

## 構成

```
src/main/java/com/example/applicant_web
 ├ MenuController.java        ホーム画面
 ├ ApplicantController.java   応募者管理(Controller)
 ├ ApplicantService.java      応募者管理(Service)
 ├ ApplicantMapper.java       応募者管理(Mapper)
 ├ Applicant.java             応募者(Entity)
 ├ UserController.java        ID管理(Controller)
 ├ UserService.java           ID管理(Service)
 ├ UserMapper.java            ID管理(Mapper)
 └ User.java                  ID(Entity)

src/main/resources
 ├ mapper/                    MyBatisのSQL(XML)
 ├ templates/                 画面(Thymeleaf)
 └ application.properties     設定
```

## 画面とURL

| 画面 | URL |
|---|---|
| ホーム | `/` |
| 応募者一覧 | `/applicants` |
| ID一覧 | `/users` |
| ID登録 | `/users/new` |
| ID編集 | `/users/{id}/edit` |

## 起動方法

### 1. 前提

- JDK 21
- MySQL 8.4

### 2. データベースの準備

MySQLにログインして、次を実行します。`任意のパスワード` の部分は、好きなものに置き換えてください。

```sql
CREATE DATABASE applicant_db DEFAULT CHARACTER SET utf8mb4;

CREATE USER 'applicant_app'@'localhost' IDENTIFIED BY '任意のパスワード';
GRANT ALL PRIVILEGES ON applicant_db.* TO 'applicant_app'@'localhost';

USE applicant_db;

CREATE TABLE applicants (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_cd VARCHAR(20) NOT NULL UNIQUE,
    user_name VARCHAR(100) NOT NULL
);
```

### 3. アプリの起動

DBのパスワードは、ファイルに書かず環境変数で渡します。

```powershell
$env:DB_PASSWORD='上で設定したパスワード'
.\mvnw.cmd spring-boot:run
```

起動したら、ブラウザで `http://localhost:8080` を開きます。

## 工夫した点

- パスワードを `application.properties` に直接書かず、環境変数で渡すようにした
- IDコードの重複は、DBの `UNIQUE` 制約で防ぎ、`DuplicateKeyException` を `IllegalArgumentException` に変換して、画面にメッセージを表示するようにした
- ID管理の登録と編集で、同じフォーム(`user-form.html`)を使い回した
- JPA + H2 で作った最初のバージョンを、現場で使われることの多いMyBatis + MySQL に置き換え、Controller → Service → Mapper の構成に整理した。

## 今後の予定

- ID管理の削除・検索
- 応募者の追加・削除を、GETからPOSTに変更
- 入力チェックの拡充、排他制御