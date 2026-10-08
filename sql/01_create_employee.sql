CREATE TABLE employee (
    id                  BIGINT       NOT NULL AUTO_INCREMENT COMMENT '社員ID',
    employee_cd         VARCHAR(20)  NOT NULL COMMENT '社員コード',
    employee_name       VARCHAR(100) NOT NULL COMMENT '社員名',
    email               VARCHAR(255) NOT NULL COMMENT 'メールアドレス',
    hire_date           DATE         NOT NULL COMMENT '入社日',
    retire_date         DATE         NULL     COMMENT '退職日',
    created_at          DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    updated_at          DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日時',
    deleted_at          DATETIME     NULL     COMMENT '削除日時(NULL=未削除)',
    active_employee_cd  VARCHAR(20)
        GENERATED ALWAYS AS (IF(deleted_at IS NULL, employee_cd, NULL)) STORED
        COMMENT '未削除社員のみの社員コード(重複チェック用)',
    PRIMARY KEY (id),
    UNIQUE KEY uk_employee_active_cd (active_employee_cd)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='社員';
