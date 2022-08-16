create table rivers(
id serial primary key,
name varchar(255),
area text,
length money
);

insert into rivers (name, area, length) values ('Sena', 'France', 18000);
update rivers set length = '17000';
insert into rivers (name, area, length) values ('Moscow', 'Russia', 14000);
insert into rivers (name, area, length) values ('Neva', 'Russia', 10000);
delete from rivers;
