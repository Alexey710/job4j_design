create database group_by;

create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('smartphone', 75000.99);
insert into devices(name, price) values ('tablet', 105000.00);
insert into devices(name, price) values ('player', 35000.50);

insert into people(name) values ('Ivan');
insert into people(name) values ('Stepan');
insert into people(name) values ('Maria');
insert into people(name) values ('Ashot');

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (2, 2);
insert into devices_people(device_id, people_id) values (2, 3);
insert into devices_people(device_id, people_id) values (3, 3);
insert into devices_people(device_id, people_id) values (1, 4);

--Используя агрегатные функции вывести среднюю цену устройств;
select avg(price) from devices;

--Используя группировку вывести для каждого человеку среднюю цену его устройств
select p.name, avg(d.price) average_price
from devices d, people p, devices_people dp where dp.device_id=d.id and dp.people_id=p.id
group by p.name;
-- Дополнить запрос п.4. условием, что цена устройства должны быть больше 5000
select p.name, avg(d.price) average_price
from devices d, people p, devices_people dp where dp.device_id=d.id and dp.people_id=p.id
group by p.name having avg(d.price) > 5000;