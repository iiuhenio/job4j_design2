create table type(
	id serial primary key,
	name text
);

create table product(
	id serial primary key,
	name text,
	type_id int references type(id),
	expired_date timestamp,
	price float
);

insert into type (name) values ('сыр');
insert into type (name) values ('мороженое');
insert into type (name) values ('молоко');

insert into product (type_id, "name", price, expired_date)
VALUES (1, 'plavleniy', 30, date '2021-09-08');
insert into product (type_id, "name", price, expired_date)
VALUES (1, 'mocarella', 40, date '2023-08-09');

insert into product (type_id, "name", price, expired_date)
VALUES (2, 'plombir', 20, date '2020-09-10');
insert into product (type_id, "name", price, expired_date)
VALUES (2, 'eskimo', 50, date '2024-07-06');
insert into product (type_id, "name", price, expired_date)
VALUES (2, 'мороженое', 55, date '2022-07-06');

insert into product (type_id, "name", price, expired_date)
VALUES (3, 'packet', 70, date '2019-07-05');
insert into product (type_id, "name", price, expired_date)
VALUES (3, 'bottle', 100, date '2023-03-07');

select product.name, expired_date, price, type.name from product
inner join type on type.id = type_id
where type.name = 'сыр'

select product.name, expired_date, price, type.name from product
inner join type on type.id = type_id
where product.name like 'мороженое';

select product.name, expired_date, price, type.name from product
inner join type on type.id = type_id
where expired_date < current_date;

select type.name, count(type.name) as Количество from product
inner join type on type.id = type_id
group by 1

select product.name, expired_date, price, type.name from product
inner join type on type.id = type_id
where type.name = 'сыр' or type.name = 'молоко'

select type.name from product
inner join type on type.id = type_id
group by 1
having count(type.name) < 10

select product.name, type.name from product
inner join type on type.id = type_id




