create table orders(
    id serial primary key,
    name varchar(255)
);

create table orderer(
    id serial primary key,
    name varchar(255),
    position_id int references orders(id)
);

insert into orders(name) values ('12345');
insert into orderer(name, position_id) VALUES ('Eugene', 1);

select * from orderer;

select * from orders where id in (select id from orderer);