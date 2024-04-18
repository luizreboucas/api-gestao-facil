CREATE TABLE IF NOT EXISTS exits (
    id SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    value NUMERIC(15,2) NOT NULL,
    exit_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    type VARCHAR(100),
    company_id BIGINT,
    CONSTRAINT fk_company_exit FOREIGN KEY(company_id) REFERENCES companys(id)
);
