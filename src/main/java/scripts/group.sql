create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

INSERT into devices ("name", price) values ('Nokia', 100);
INSERT into devices ("name", price) values ('Samsung', 2000);
INSERT into devices ("name", price) values ('Philips', 3000);
INSERT into devices ("name", price) values ('Sony', 6000);

insert into people("name") values ('Anton');
insert into people("name") values ('Ivan');
insert into people("name") values ('Ilya');
insert into people("name") values ('Eugene');

insert into devices_people (device_id, people_id) values (1, 4);
insert into devices_people (device_id, people_id) values (2, 3);
insert into devices_people (device_id, people_id) values (3, 2);
insert into devices_people (device_id, people_id) values (4, 1);

select avg(price) from devices;

select p.name, avg(d.price) as avg_price from devices_people as dp
join people as p on dp.people_id = p.id
join devices as d on dp.device_id = d.id
group by p.name

select p.name, avg(d.price) as avg_price from devices_people as dp
join people as p on dp.people_id = p.id
join devices as d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;
