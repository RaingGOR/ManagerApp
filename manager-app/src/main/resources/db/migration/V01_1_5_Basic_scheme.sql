create schema if not exists user_management;

create table if not exists user_management.t_user(
    id  BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username varchar not null unique,
    password varchar
);

create table if not exists user_management.t_authority (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    authority varchar not null
);

create table if not exists user_management.t_user_authority(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    id_user int not null references user_management.t_user(id),
    id_authority int not null references user_management.t_authority(id),
    constraint uk_user_authority unique (id_authority,id_user)
)