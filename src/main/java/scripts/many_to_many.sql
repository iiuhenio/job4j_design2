 create table humans(
     id serial primary key,
     name varchar(255)
 );

 create table pets(
     id serial primary key,
     name varchar(255)
 );

 create table humans_pets(
     id serial primary key,
     human_id int references humans(id),
     pets_id int references pets(id)
 );

insert into humans(name) values ('Ivanov');
insert into humans(name) values ('Stepanov');
insert into humans(name) values ('Vasiliev');

insert into pets(name) values ('Dog');
insert into pets(name) values ('Cat');
insert into pets(name) values ('Bird');

insert into humans_pets(human_id, pets_id) values (1, 1);
insert into humans_pets(human_id, pets_id) values (1, 2);
insert into humans_pets(human_id, pets_id) values (1, 3);
insert into humans_pets(human_id, pets_id) values (2, 1);
insert into humans_pets(human_id, pets_id) values (2, 2);
insert into humans_pets(human_id, pets_id) values (3, 3);

