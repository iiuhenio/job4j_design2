insert into category(name) values ('category1');

insert into state(name) values ('state1');

insert into role(name) values ('role1');

insert into rules(name) values ('rules1');

insert into users(name) values ('name1');
insert into users(role_id) values (1);

insert into comments(name) values ('comments1');
insert into comments(item_id) values (1);

insert into attachs(name) values ('attachs1');
insert into attachs(item_id) values (1);

insert into role_rules(name) values ('role_rules1');
insert into role_rules(rules_id) values (1);
insert into role_rules(role_id) values (1);

insert into item(name) values ('item1');
insert into item(users_id, state_id, category_id) values (1, 1, 1);





