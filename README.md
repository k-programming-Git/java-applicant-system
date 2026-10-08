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

以下の社員情報を登録できます。

- 社員コード
- 社員名
- メールアドレス
- 入社日
- 退職日

入力値には Bean Validation を使用してチェックを行っています。

また、未削除社員と同じ社員コードを登録した場合は登録せず、

> この社員コードは既に登録されています。

というエラーメッセージを社員登録画面に表示します。

削除済み社員の社員コードは再利用できます。

### 社員詳細

以下の社員情報を表示します。

- 社員コード
- 社員名
- メールアドレス
- 入社日
- 退職日

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

### MyBatis・画面テンプレート

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
| 社員編集 | `/employees/{id}/edit` | 社員情報編集 |
| エラー | - | 社員が存在しない場合などに表示 |

## データベース

データベース名：

```text
applicant_db
```

アプリケーション用DBユーザー：

```text
applicant_app
```

### employeeテーブル

主なカラム：

| カラム | 内容 |
|---|---|
| `id` | 社員ID |
| `employee_cd` | 社員コード |
| `employee_name` | 社員名 |
| `email` | メールアドレス |
| `hire_date` | 入社日 |
| `retire_date` | 退職日 |
| `created_at` | 作成日時 |
| `updated_at` | 更新日時 |
| `deleted_at` | 削除日時 |
| `active_employee_cd` | 未削除社員の社員コード重複チェック用 |

社員コードについては、未削除社員のみ一意となるようDB側でも制約を設定しています。

削除済み社員については `deleted_at` に日時を設定し、物理削除は行いません。

## DBセットアップ

DBを再現するためのSQLファイルを `sql/` に用意しています。

```text
sql/
├── 01_create_employee.sql
└── 02_insert_test_data.sql
```

### 1. employeeテーブルを作成

`01_create_employee.sql` をMySQLで実行して、`employee` テーブルを作成します。

### 2. テストデータを投入

`02_insert_test_data.sql` をMySQLで実行して、開発・動作確認用の社員データを登録します。

このSQLでは、以下のテストデータを登録します。

- EMP001：Yamada Taro
- EMP002：Sato Hanako
- EMP003：Suzuki Ichiro
- EMP004：Deleted Person

EMP004は論理削除された状態になるようにしています。

DBパスワードなどの認証情報は、ソースコードや `application.properties` に直接記載せず、環境変数 `DB_PASSWORD` を使用します。

## 別PCで開発を再開する場合

GitHubからプロジェクトをcloneすることで、別PCでも開発を再開できます。

### 1. GitHubからclone

PowerShellで以下を実行します。

```powershell
git clone https://github.com/k-programming-Git/java-applicant-system.git
cd java-applicant-system
```

### 2. Javaのバージョンを確認

Java 21がインストールされていることを確認します。

```powershell
java -version
```

### 3. MySQLを準備

MySQL 8.4をインストールし、アプリケーションから接続できる状態にします。

以下のデータベースとDBユーザーを用意します。

```text
データベース：applicant_db
DBユーザー：applicant_app
```

### 4. employeeテーブルを作成

プロジェクト内の以下のSQLをMySQLで実行します。

```text
sql/01_create_employee.sql
```

これにより `employee` テーブルが作成されます。

### 5. テストデータを投入

動作確認用のデータが必要な場合は、以下のSQLを実行します。

```text
sql/02_insert_test_data.sql
```

### 6. DBパスワードを環境変数に設定

PowerShellで、`applicant_app` のDBパスワードを設定します。

```powershell
$env:DB_PASSWORD='設定したDBパスワード'
```

DBパスワードはGitHubには保存しません。

ここまでで、別PCでの開発環境の準備は完了です。

## アプリケーションの起動

### 1. DBパスワードを環境変数に設定

PowerShellで以下を実行します。

```powershell
$env:DB_PASSWORD='設定したDBパスワード'
```

### 2. Spring Bootを起動

プロジェクトのルートディレクトリで以下を実行します。

```powershell
.\mvnw.cmd spring-boot:run
```

このプロジェクトでは Maven Wrapper を使用しているため、Maven本体を個別にインストールする必要はありません。

### 3. ブラウザでアクセス

```text
http://localhost:8080
```

## 実装したイベント

| イベントID | 内容 |
|---|---|
| E_001 | 社員一覧表示 |
| E_002 | 社員検索 |
| E_003 | 社員登録画面表示 |
| E_004 | 社員登録 |
| E_005 | 社員詳細表示 |
| E_006 | 社員編集画面表示 |
| E_007 | 社員更新 |
| E_008 | 社員論理削除 |

## バリデーション

社員登録・社員更新では Bean Validation を使用しています。

主なチェック内容：

- 社員コード：必須、20文字以下、英数字・ハイフン
- 社員名：必須、100文字以下
- メールアドレス：必須、メール形式、255文字以下
- 入社日：必須
- 退職日：任意

また、HTMLの `input type="date"` と Java の `LocalDate` の形式を合わせるため、`yyyy-MM-dd` 形式で日付を扱っています。

## 開発で工夫した点

### 1. 論理削除

社員データを物理削除せず、`deleted_at` を設定することで削除状態を管理しています。

これにより、削除済み社員の社員コードを再利用できるようにしています。

### 2. 社員コードの重複チェック

DBのUNIQUE制約による重複エラーを `DuplicateKeyException` で受け取り、画面上に利用者向けのメッセージを表示しています。

```text
この社員コードは既に登録されています。
```

### 3. エラー画面

存在しない社員や削除済み社員へアクセスした場合に、専用のエラー画面を表示するようにしています。

### 4. 層を分けた構成

Controller、Service、Mapperを分離し、それぞれの役割を明確にしています。

```text
Controller
  ↓
Service
  ↓
Mapper
  ↓
MySQL
```

## 設計資料

開発時に以下の設計資料を作成しています。

- 企画書
- 要件定義書
- 画面項目定義書
- 画面一覧
- 画面遷移図
- テーブル定義書
- イベント設計書

## 今後の予定

- テスト仕様書の作成
- テストケースに基づく動作確認
- テスト結果の記録
- 必要に応じた追加改善