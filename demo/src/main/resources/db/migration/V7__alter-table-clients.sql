ALTER TABLE clients
ADD COLUMN company_id BIGINT;

ALTER TABLE clients
ADD CONSTRAINT fk_client_company
FOREIGN KEY(company_id) REFERENCES companys(id);