create table user_general(
    id uuid not null,
    name varchar(100) not null,
    email varchar(100) not null,
    password varchar(30) not null,
    cpf varchar(20) not null,
    cidade varchar(100) not null,
    estado varchar(100) not null,
    telefone varchar(15) not null,
    presta_servico boolean not null,
    active boolean not null,

    primary key (id)
);