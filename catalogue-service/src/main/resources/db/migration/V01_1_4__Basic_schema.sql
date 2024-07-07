create schema if not exists catalogue;

create table if not exists catalogue.t_product(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name varchar(50) not null,
    description varchar(1000)
)