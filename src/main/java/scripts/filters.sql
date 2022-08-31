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

select * from product where type_id = 1;

select * from product where name like 'мороженое';

select * from product where expired_date < '01.09.2022';

select name, price from product where price = (select max(price) from product);

select t.name, count(p.name)
from product as p
join type as t
on p.type_id = t.id
group by t.name

select * from product where type_id != 2;

select t.name тип_продукта, COUNT(p.type_id)
from product p
inner join type t
on p.type_id = t.id
group by t.name
having COUNT(p.type_id) < 10;

select pp.name, p.name
from product as pp join type as p on pp.type_id = p.id




