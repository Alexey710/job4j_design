insert into user1(name, role_id) VALUES ('User1', 1);
insert into role(name) VALUES ('Role1');
insert into rules(name) VALUES ('Role1');
insert into role_rules(role_id, rules_id) VALUES (1, 1);
insert into item(name, user_id, category_id) VALUES ('Item1', 1, 1);
insert into comments(name, item_id) VALUES ('Comments1', 1);
insert into attachs(name, item_id) VALUES ('Attachs1', 1);
insert into state(name, item_id) VALUES ('State1', 1);
insert into category(name, item_id) VALUES ('Category1', 1);
