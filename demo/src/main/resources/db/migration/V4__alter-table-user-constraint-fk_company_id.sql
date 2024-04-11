alter table users
add constraint fk_company_user
foreign key(company_id) references companys(id)