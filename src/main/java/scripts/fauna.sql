create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna("name", avg_age, discovery_date)
values ('fish_shark', 25, date '1506-08-04');

insert into fauna("name", avg_age, discovery_date)
values ('fish_whale', 40, date '998-07-05');

insert into fauna("name", avg_age, discovery_date)
values ('bird_seagul', 10, date '1200-06-03');

insert into fauna("name", avg_age, discovery_date)
values ('bird_duck', 8, date '1305-04-09');

insert into fauna("name", avg_age, discovery_date)
values ('animal_camel', 25, date '205-09-03');

insert into fauna("name", avg_age, discovery_date)
values ('animal_jerboa', 21, date '1805-01-02');

select * from fauna where name like 'fish%';

select * from fauna where avg_age >= 10 and avg_age <= 21;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '01.01.1950';
