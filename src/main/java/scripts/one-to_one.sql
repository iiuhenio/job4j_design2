create table country(
    id serial primary key,
    name text
);

create table president(
    id serial primary key,
    surname text
);

create table country_president(
    id serial primary key,
    country_id int references country(id) unique,
    president_id int references president(id) unique
);