INSERT INTO employee
    (employee_cd, employee_name, email, hire_date, retire_date)
VALUES
    ('EMP001', 'Yamada Taro',    'yamada@example.com',  '2020-04-01', NULL),
    ('EMP002', 'Sato Hanako',    'sato@example.com',   '2021-04-01', '2025-03-31'),
    ('EMP003', 'Suzuki Ichiro',  'suzuki@example.com', '2022-10-01', NULL),
    ('EMP004', 'Deleted Person', 'deleted@example.com', '2019-04-01', NULL);

UPDATE employee
SET deleted_at = NOW(),
    updated_at = NOW()
WHERE employee_cd = 'EMP004';