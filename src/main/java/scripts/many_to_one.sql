create table orderer(
    id serial primary key,
    name varchar(255)
);

create table orders(
    id serial primary key,
    name varchar(255),
    position_id int references orderer(id)
);

insert into orderer(name) values ('Eugene');
insert into orders(name, position_id) VALUES ('1125', 1);

select * from orders;

select * from orderer where id in (select id from orders);