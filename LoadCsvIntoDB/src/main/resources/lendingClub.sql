DROP TABLE LENDINGCLUB IF EXISTS;

CREATE TABLE LENDINGCLUB  (
    member_id INT PRIMARY KEY,
    loan_amnt VARCHAR(2550),
    funded_amnt_inv VARCHAR(2550),
    term VARCHAR(2550),
    int_rate VARCHAR(2550),

    installment VARCHAR(2550),
    grade VARCHAR(2550),
    emp_title VARCHAR(2550),
    emp_length VARCHAR(2550),
    home_ownership VARCHAR(2550),

    annual_inc VARCHAR(2550),
    verification_status VARCHAR(2550),
    issue_d VARCHAR(2550),
    loan_status VARCHAR(2550),
    descr VARCHAR2,

    purpose VARCHAR(2550),
    title VARCHAR(2550),
    addr_state VARCHAR(2550),
    last_pymnt_d VARCHAR(2550),
    last_pymnt_amnt VARCHAR(2550)
);
