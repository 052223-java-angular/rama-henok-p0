	drop table if exists users cascade;
	drop table if exists orders cascade;
	drop table if exists products cascade;
	drop table if exists reviews cascade;

create table users(
user_id VARCHAR primary key,
first_name VARCHAR(50),
last_name VARCHAR(50),
password VARCHAR(50)
);

create table products(
product_id VARCHAR primary key,
product_name VARCHAR(50),
price decimal,
category VARCHAR
);

create table orders(
order_id VARCHAR primary key,
quantity INT,
user_id VARCHAR,
product_id VARCHAR,
foreign key (user_id) references users (user_id),
foreign key (product_id) references products (product_id)
);



create table reviews(
review_id VARCHAR primary key,
user_id VARCHAR,
product_id VARCHAR,
rate INT,
comment VARCHAR, 
foreign key (user_id) references users (user_id),
foreign key (product_id) references products (product_id)
);