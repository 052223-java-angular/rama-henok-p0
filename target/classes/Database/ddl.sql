	drop table if exists customers cascade;
	drop table if exists orders cascade;
	drop table if exists products cascade;
	drop table if exists reviews cascade;

create table customers(
customer_id SERIAL primary key,
first_name VARCHAR(50),
last_name VARCHAR(50),
password VARCHAR(50)
);

create table products(
product_id SERIAL primary key,
product_name VARCHAR(50),
price decimal,
category VARCHAR
);

create table orders(
order_id SERIAL primary key,
quantity INT,
customer_id INT,
product_id INT,
foreign key (customer_id) references customers (customer_id),
foreign key (product_id) references products (product_id)
);



create table reviews(
review_id SERIAL primary key,
customer_id INT,
product_id INT,
rate INT,
comment VARCHAR, 
foreign key (customer_id) references customers (customer_id),
foreign key (product_id) references products (product_id)
);