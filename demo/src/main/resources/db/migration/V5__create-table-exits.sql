create table if not exists exits(
    id bigint not null auto_increment,
    description varchar(255) not null,
    value numeric(15,2) not null,
    exit_date datetime default CURRENT_TIMESTAMP,
    type varchar(100),
    company_id bigint,
    primary key(id),
    constraint fk_company_exit
    foreign key(company_id) references companys(id)
)