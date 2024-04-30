CREATE TABLE IF NOT EXISTS supliers(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cnpj VARCHAR(100) NOT NULL,
    company_id BIGINT NOT NULL,
    CONSTRAINT fk_suplier_company FOREIGN KEY (company_id) REFERENCES companys(id)
);

ALTER TABLE exits
ADD COLUMN suplier_id BIGINT;

ALTER TABLE exits
ADD CONSTRAINT fk_exits_supliers
FOREIGN KEY (suplier_id) REFERENCES supliers(id);