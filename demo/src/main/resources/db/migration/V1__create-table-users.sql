CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    role VARCHAR(100),
    name VARCHAR(255),
    email VARCHAR(100),
    password VARCHAR(255),
    cpf VARCHAR(100),
    phone_number VARCHAR(100),
    company_id BIGINT NOT NULL
);