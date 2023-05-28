	drop table if exists roles cascade;
    drop table if exists users  cascade;
    drop table if exists categories cascade;
	drop table if exists products cascade;
	drop table if exists carts cascade; 
	drop table if exists cartitems cascade;
	drop table if exists orders cascade;
	drop table if exists orderitems cascade;
	drop table if exists reviews cascade;

    
create table roles(
role_id VARCHAR primary key,
role_name VARCHAR
);

insert into roles (role_id, role_name) values ('1', 'ADMIN');
insert into roles (role_id, role_name) values ('2', 'USER');

create table users(
user_id VARCHAR primary key,
user_name VARCHAR not null unique,
password VARCHAR not null,
role_id VARCHAR not null,
FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

insert into users (user_id, user_name, password, role_id ) values ('1', 'Niki', 'kokokks8', '2');
insert into users (user_id, user_name, password, role_id ) values ('2', 'Puma Joggers', '14.99', '2');


create table categories(
category_id int primary key,
category_name VARCHAR
);

insert into categories (category_id, category_name) values ('1', 'CLOTHING');
insert into categories (category_id, category_name) values ('2', 'ELECTRONICS');
insert into categories (category_id, category_name) values ('3', 'EYEGLASS');

create table products(
product_id serial primary key,
product_name VARCHAR not null unique,
price float not null,
stock int not null,
category_id int not null,
FOREIGN KEY (category_id) REFERENCES categories(category_id)
);



insert into products (product_id, product_name, price, category_id ) values ('1', 'Nike Jacket', '144.99', '1');
insert into products (product_id, product_name, price, category_id ) values ('2', 'Puma Joggers', '14.99', '1');

insert into products (product_id, product_name, price, category_id ) values ('3', 'Iphone 14 Pro Max', '1299.99', '2');
insert into products (product_id, product_name, price, category_id ) values ('4', 'Dell XPS 15', '2499.99', '2');

insert into products (product_id, product_name, price, category_id ) values ('5', 'RayBan Eyeglass', '120.99', '3');
insert into products (product_id, product_name, price, category_id ) values ('6', 'Computer Eyeglass', '44', '3');


create table carts(
cart_id serial primary key,
user_id VARCHAR,
FOREIGN KEY (user_id) REFERENCES users(user_id)
);

insert into carts (cart_id, user_id) values ('39', '1');
insert into carts (cart_id, user_id) values ('9', '2');

create table cartitems(
cart_item_id serial primary key,
product_name VARCHAR not null unique,
price float not null,
quantity int not null,
cart_id int,
product_id int, 
FOREIGN KEY (cart_id) REFERENCES carts(cart_id),
FOREIGN KEY (product_id) REFERENCES products(product_id)
);

insert into cartitems (cart_item_id, product_name, price, quantity, cart_id, product_id ) values ('69', 'Computer Eyeglass', '44', '39', '39', '6');


create table orders(
order_id serial primary key,
product_name Varchar,
total_cost float not null,
order_time timestamp,
user_id varchar 
);

insert into orders (order_id, product_name, total_cost, order_time, user_id) values ('69', 'Computer Eyeglass', '88', '05/15/2023', '2');


create table orderitems(
order_item_id serial primary key,
quantity int not null,
price float not null,
order_id int,
product_id int, 
FOREIGN KEY (order_id) REFERENCES orders(order_id),
FOREIGN KEY (product_id) REFERENCES products(product_id)
);

insert into orderitems (order_item_id, quantity, price, order_id, product_id) values ('69', '2', '44', '69', '6');


create table reviews(
review_id serial primary key,
rating int,
comment varchar,
order_time timestamp,
product_id int,
user_id varchar,
FOREIGN KEY (product_id) REFERENCES products(product_id),
FOREIGN KEY (user_id) REFERENCES users(user_id)
);

insert into reviews (review_id, rating, comment, order_time, product_id, user_id) values ('100', '2', 'This is cool eyeglass', '2023-05-27', '6', '2');




