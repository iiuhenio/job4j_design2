create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.13
		where id = (select id from inserted);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
	referencing new table as inserted
    for each statement
    execute procedure tax();

create or replace function tax2()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.3
		where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax2_trigger
    BEFORE insert on products
    for each row
    execute procedure tax2();



create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function hp()
    returns trigger as
$$
    BEGIN
        insert into history_of_price("name", price, date) values (new.name, new.price, datetime.now);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger hp_trigger
    after insert on history_of_price
    for each row
    execute procedure hp();

