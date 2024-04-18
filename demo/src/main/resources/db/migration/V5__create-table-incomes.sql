CREATE TABLE IF NOT EXISTS incomes (
    id SERIAL PRIMARY KEY,
    value NUMERIC(15,2) NOT NULL,
    company_id BIGINT NOT NULL,
    income_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description VARCHAR(255),
    CONSTRAINT fk_company_income FOREIGN KEY(company_id) REFERENCES companys(id)
);
