create database car;

--Создать структур данных в базе. Таблицы: Кузов. Двигатель, Коробка передач.
create table car_body(
 id serial primary key,
 name varchar(255)
 );
insert into car_body(name) values ('body1'), ('body2'), ('body3');

create table engine(
 id serial primary key,
 name varchar(255)
 );
insert into engine(name) values ('engine1'), ('engine2'), ('engine3'), ('engine4');

create table change_gear(
 id serial primary key,
 name varchar(255)
 );
insert into change_gear(name) values ('change_gear1'), ('change_gear2');

--Создать структуру Машина. Машина не может существовать без данных из п.1.
create table car(
 id serial primary key,
 name varchar(255),
 car_body_id int references car_body(id) not null,
 engine_id int references engine(id) not null,
 change_gear_id int references change_gear(id) not null
 );
 insert into car(name, car_body_id, engine_id, change_gear_id)
  values ('car1', 1, 1, 1), ('car2', 2, 2, 2);
UPDATE car SET change_gear_id = 1 WHERE change_gear_id = 2;
--1. Вывести список всех машин и все привязанные к ним детали.
select c.name, cb.name, e.name, cg.name
from car c, car_body cb, engine e, change_gear cg
where c.car_body_id = cb.id
   and c.engine_id = e.id
   and c.change_gear_id = cg.id;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
--неиспользованные кузова
select cb.name as idle_car_body from car c
right join car_body cb on c.car_body_id = cb.id
where c.id is null;
--неиспользованные двигатели
select e.name as idle_engine from car c
right join engine e on c.engine_id = e.id
where c.id is null;
--неиспользованные коробки передач
select cg.name as idle_change_gear from car c
right join change_gear cg on c.change_gear_id = cg.id
where c.id is null;



--неиспользованные кузова, двигатели, коробки передач в одной таблице
select e.name as idle_engine, cb.name as idle_car_body, cg.name as idle_change_gear from car c
full outer join engine e on c.engine_id = e.id
full outer join car_body cb on c.car_body_id = cb.id
full outer join change_gear cg on c.change_gear_id = cg.id
where c.id is null;
