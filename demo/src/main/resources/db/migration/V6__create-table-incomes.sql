create table incomes(
    id bigint not null auto_increment,
    value numeric(15,2) not null,
    company_id bigint not null,
    income_date datetime default CURRENT_TIMESTAMP,
    description varchar(255),
    primary key (id),
    foreign key(company_id) references companys(id)
)
