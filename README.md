# 社員管理システム

Java / Spring Boot / Thymeleaf / MyBatis / MySQL を使用して開発した社員管理Webアプリケーションです。

社員情報の一覧表示、検索、登録、詳細表示、編集、論理削除までの一連の業務を実装しています。

## 主な機能

### 社員一覧・検索
- 社員一覧の表示
- 社員コードによる完全一致検索
- 社員名による部分一致検索
- 社員コードから社員詳細画面への遷移
- 削除済み社員は一覧に表示しない

### 社員登録
- 社員コード
- 社員名
- メールアドレス
- 入社日
- 退職日

の登録が可能です。

入力値には Bean Validation を使用してチェックを行っています。

また、未削除社員と同じ社員コードを登録した場合は登録せず、

> この社員コードは既に登録されています。

というエラーメッセージを社員登録画面に表示します。

削除済み社員の社員コードは再利用できます。

### 社員詳細
- 社員コード
- 社員名
- メールアドレス
- 入社日
- 退職日

を表示します。

詳細画面から社員編集・社員削除を行えます。

### 社員編集
社員名、メールアドレス、入社日、退職日を編集できます。

社員コードは変更不可としています。

### 社員削除
物理削除ではなく論理削除を採用しています。

削除時には `deleted_at` と `updated_at` を更新し、削除済み社員を通常の一覧・詳細画面から除外します。

### エラー画面
存在しない社員IDや削除済み社員のURLへアクセスした場合は、エラー画面を表示します。

---

## 使用技術

- Java 21
- Spring Boot 4.0.6
- Thymeleaf
- MyBatis
- MySQL 8.4
- Maven
- Maven Wrapper
- Bean Validation

## システム構成

```text
Browser
   ↓
Controller
   ↓
Service
   ↓
Mapper (MyBatis)
   ↓
MySQL
```

Controller → Service → Mapper → MySQL の構成で、各層の役割を分離しています。

### 主なクラス

```text
src/main/java/com/example/applicant_web
├── MenuController.java
├── EmployeeController.java
├── EmployeeService.java
├── EmployeeMapper.java
└── Employee.java
```

### MyBatis

```text
src/main/resources
├── mapper
│   └── EmployeeMapper.xml
├── templates
│   ├── menu.html
│   ├── employee-list.html
│   ├── employee-form.html
│   ├── employee-detail.html
│   ├── employee-edit.html
│   └── error.html
└── application.properties
```

## 画面・URL

| 画面 | URL | 内容 |
|---|---|---|
| ホーム | `/` | メニュー画面 |
| 社員一覧 | `/employees` | 社員一覧・検索 |
| 社員登録 | `/employees/new` | 社員登録 |
| 社員詳細 | `/employees/{id}` | 社員詳細 |
| 社員編集 | `/employees