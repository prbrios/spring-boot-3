create table tb_user (
    id serial primary key,
    username varchar(255) unique,
    password varchar(255)
);

create table tb_role (
    id serial primary key,
    name varchar(255)
);

create table tb_user_role (
    id_user int references tb_user (id),
    id_role int references tb_role (id)
);

insert into tb_role (name) values ('ROLE_ADMIN');
insert into tb_user (username, password) values ('admin', '$2a$12$aeLtzYeQsz2FsBWA8idXG.dB..iOMq50xLVHDkPdIs0opmoaKZqFu');
insert into tb_user_role (id_user, id_role) values (1, 1);
