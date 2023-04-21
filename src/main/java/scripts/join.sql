create table departaments(
    id serial primary key,
    name text
);

create table employees(
    id serial primary key,
    name text,
	departaments_id int references departaments(id)
);

insert into departaments(name) values ('Sale');
insert into departaments(name) values ('Marketing');
insert into departaments(name) values ('Service');
insert into departaments(name) values ('Chiling');

insert into employees(name, departaments_id) values ('Ivanov', 1);
insert into employees(name, departaments_id) values ('Petrov', 1);
insert into employees(name, departaments_id) values ('Sidorov', 1);

insert into employees(name, departaments_id) values ('Antonov', 2);
insert into employees(name, departaments_id) values ('Nikolaev', 2);

insert into employees(name, departaments_id) values ('Stalone', 3);

select * from employees e left join departaments ON departaments.id = e.departaments_id;
select * from departaments d right join employees e on d.id = e.departaments_id;
select * from employees e full join departaments d on e.departaments_id = d.id;
select * from employees e cross join departaments d;

select departaments.name
from departaments left join employees on departaments_id=departaments.id where employees.name is null;

SELECT e.id, e.name, d.name AS department
FROM employees AS e LEFT JOIN departaments AS d ON e.departaments_id = d.id;

SELECT e.id, e.name, d.name AS departaments
FROM departaments AS d RIGHT JOIN employees AS e ON e.departaments_id = d.id;

create table teens(
	id serial primary key,
	name text,
	gender text
);

insert into teens(name, gender) values ('Maksim', 'male');
insert into teens(name, gender) values ('Andrey', 'male');
insert into teens(name, gender) values ('Fedor', 'male');

insert into teens(name, gender) values ('Anna', 'female');
insert into teens(name, gender) values ('Katya', 'female');
insert into teens(name, gender) values ('Sveta', 'female');

select m.name, f.name
from teens m
cross join teens f
where m.gender != f.gender;