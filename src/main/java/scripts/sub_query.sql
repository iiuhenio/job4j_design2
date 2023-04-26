CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

INSERT INTO customers values (1, 'Ivan', 'Ivanov', 25, 'Russia'),
(2, 'Petr', 'Petrov', 42, 'Ukraine'),
(3, 'Anna', 'Stepanova', 18, 'France');

select * from customers
where age = (select min(age) from customers);



CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders values (1, 10, 1), (2, 20, 1), (3, 15, 2);

select * from customers
where customers.id not in (select customer_id from orders);

