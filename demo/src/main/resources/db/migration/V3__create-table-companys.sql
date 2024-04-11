create table companys(
    id bigint not null auto_increment,
    name varchar(255) not null,
    phone_number varchar(100),
    cnpj varchar(100) not null,
    state varchar(2),
    city varchar(100),
    street varchar(100),
    number varchar(100),
    complement varchar(255),
    primary key(id)
)