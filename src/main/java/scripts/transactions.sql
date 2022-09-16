create table cars (
    id serial primary key,
    name text,
    producer text,
    count integer default 0,
    price integer
);

insert into cars (name, producer, count, price) VALUES ('Corolla', 'Toyota', 1, 100);
insert into cars (name, producer, count, price) VALUES ('Focus', 'Ford', 5, 90);
insert into cars (name, producer, count, price) VALUES ('Qashqai', 'Nissan', 7, 115);

begin transaction;

savepoint first_savepoint;

insert into cars (name, producer, count, price) VALUES ('Vitara', 'Suzuki', 3, 110);

savepoint second_savepoint;

delete from cars where price = 100;
update cars set price = 75 where name = 'Focus';

savepoint third_savepoint;

insert into cars (name, producer, count, price) VALUES ('Polo', 'VW', 3, 75);

select * from cars;

rollback to third_savepoint;
select * from cars;

rollback to second_savepoint;
select * from cars;

rollback to first_savepoint;
select * from cars;

commit;