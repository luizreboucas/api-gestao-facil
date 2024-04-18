CREATE TABLE IF NOT EXISTS companys (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(100),
    cnpj VARCHAR(100) NOT NULL,
    state VARCHAR(2),
    city VARCHAR(100),
    street VARCHAR(100),
    number VARCHAR(100),
    complement VARCHAR(255)
);
