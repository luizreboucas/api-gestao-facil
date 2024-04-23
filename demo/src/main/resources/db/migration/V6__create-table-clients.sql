CREATE TABLE clients(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cnpj VARCHAR (30) NOT NULL
);

ALTER TABLE incomes
ADD COLUMN client_id BIGINT;

ALTER TABLE incomes
ADD CONSTRAINT fk_income_client FOREIGN KEY(client_id) REFERENCES clients(id);