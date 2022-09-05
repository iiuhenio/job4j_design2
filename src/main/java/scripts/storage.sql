create table car_bodies(
	id serial primary key,
	name text
);

create table car_engines(
	id serial primary key,
	name text
);

create table car_transmissions(
	id serial primary key,
	name text
);

create table cars(
	id serial primary key,
	name text,
	body_id int REFERENCES car_bodies(id),
	engine_id int REFERENCES car_bodies(id),
	transmission_id int REFERENCES car_bodies(id)
);

insert into car_bodies (name) values ('sedan'), ('hatchback'), ('wagon'), ('picqup');
insert into car_engines (name) values ('179'), ('249'), ('149'), ('650');
insert into car_transmissions (name) values ('automatic'), ('mechanic'), ('SVT');
insert into cars (name, body_id, engine_id, transmission_id) VALUES
('Toyota', 1, 1, 1), ('Nissan', 2, 2, 2);
insert into cars (name, body_id, engine_id) VALUES ('BMW', 3, 3);

select c.id, c.name as "car_name", b.name as "body_name", e.name as "engine_name", t.name as "transmission_name"
from cars c left join car_bodies b on c.body_id = b.id
left join car_engines e on c.engine_id = e.id
left join car_transmissions t on c.transmission_id = t.id;

select b.name as body_name
from car_bodies b left join cars c
on c.body_id = b.id
where c.name is null;

select e.name
from car_engines e left join cars c
on c.engine_id = e.id
where c.name is null;

select t.name
from car_transmissions t left join cars c
on c.transmission_id = t.id
where c.name is null;

