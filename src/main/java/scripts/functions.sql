create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;

call insert_data('product_1', 'producer_2', 15, 32);
call insert_data('product_2', 'producer_2', 15, 32);
call insert_data('product_3', 'producer_2', 15, 32);

create or replace procedure delete_data(p_id integer)
language 'plpgsql'
as $$
    BEGIN
		delete from products
		where id = p_id;
    END;
$$;

call delete_data(1);

select * from products;

delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

create or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

select f_insert_data('product_1', 'producer_1', 25, 50);
select f_insert_data('product_2', 'producer_1', 25, 50);
select f_insert_data('product_3', 'producer_1', 25, 50);

create or replace function f_delete_data(p_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        delete from products where id = p_id;
		select into result from products where id = p_id;
        return result;
    end;
$$;

select f_delete_data(2);




