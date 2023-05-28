drop table if exists users cascade;
drop table if exists orders cascade;
drop table if exists products cascade;
drop table if exists reviews cascade;

create table users(
user_id VARCHAR primary key,
user_name VARCHAR(50) not null unique,
password VARCHAR(50) not null,
role_id Integer default 1
);

insert into users ( user_id, user_name, password, role_id) Values ( '12k', 'rs', 'rspass', '1');