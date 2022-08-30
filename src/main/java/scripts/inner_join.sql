CREATE table president(
    id serial primary key,
    name text,
    surname text
);

create table country(
    id serial primary key,
    name varchar(255),
    president_id int references president(id) unique
);

insert into president(name, surname) values ('Joe', 'Biden');
insert into president(name, surname) values ('Vladimir', 'Putin');
insert into president(name, surname) values ('Angela', 'Merkel');

insert into country(name, president_id) values ('USA', 1);
insert into country(name, president_id) values ('Russia', 2);
insert into country(name, president_id) values ('Germany', 3);
insert into country(name) values ('Italy');
insert into country(name) values ('France');

select pp.name, p.name, p.surname
from country as pp join president as p on pp.president_id = p.id;

select pp.name as Название, p.name as Имя, p.surname as Фамилия
from country as pp join president as p on pp.president_id = p.id;

select pp.name as "Название страны", p.name, p.surname Фамилия
from country pp join president p on pp.president_id = p.id;