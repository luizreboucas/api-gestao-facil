ALTER TABLE users
ADD CONSTRAINT fk_company_user FOREIGN KEY (company_id) REFERENCES companys(id);
