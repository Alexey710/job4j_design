-- user1 - role = many-to-one
create table user1 (
id serial primary key,
name varchar(255),
role_id int references role(id)
);

-- role - rules = many-to-many
create table role (
id serial primary key,
name varchar(255)
);

create table rules (
id serial primary key,
name varchar(255)
);

create table role_rules (
id serial primary key,
role_id int references role(id),
rules_id int references rules(id)
);

-- item - user1 = many-to-one
create table item (
id serial primary key,
name varchar(255),
user_id int references user1(id)
);

-- item - comments = one-to-many
create table comments (
id serial primary key,
name varchar(255),
item_id int references item(id)
);

-- item - attachs = one-to-many
create table attachs (
id serial primary key,
name varchar(255),
item_id int references item(id)
);

-- item - state = many-to-one
create table state (
id serial primary key,
name varchar(255),
item_id int references item(id)
);

-- item - category = many-to-one
create table category (
id serial primary key,
name varchar(255),
item_id int references item(id)
);