create database filters;

create table product(
 id serial primary key,
 name varchar(255),
 type_id int references product(id),
 expired_date timestamp,
 price float );

create table type_(
 id serial primary key,
 name varchar(255)
);

insert into product(
 name, type_id, expired_date, price)
 values ('голандский', 1, date '2021-01-28', 400.50);
insert into product(
 name, type_id, expired_date, price)
 values ('poccийский', 1, date '2021-01-30', 350.50);
insert into product(
 name, type_id, expired_date, price)
 values ('деревенское', 2, date '2021-01-30', 70.50);
insert into product(
 name, type_id, expired_date, price)
 values ('пастеризованное', 2, date '2021-01-27', 50.50);
insert into product(
 name, type_id, expired_date, price)
 values ('пастеризованное ЭКСТРА', 2, date '2021-02-27', 50.50);
insert into product(
 name, type_id, expired_date, price)
 values ('мороженное айс', 3, date '2021-01-30', 30.50);
insert into product(
 name, type_id, expired_date, price)
 values ('эскимо', 3, date '2021-01-29', 38.50);
insert into product(
 name, type_id, expired_date, price)
 values ('эскимо', 3, date '2021-01-30', 38.50);

insert into type(name) values ('СЫР');
insert into type(name) values ('МОЛОКО');
insert into type(name) values ('МОРОЖЕННОЕ');

--1. Написать запрос получение всех продуктов с типом "СЫР"
select * from product p inner join type t on p.type_id=t.id where t.name='МОЛОКО';
--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product p inner join type t on p.type_id=t.id where p.name like '%мороженное%';
--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product where expired_date between
        date_trunc('month', now()) + interval '1 month' and
        date_trunc('month', now()) + interval '2 month';
--второй вариант запроса
select * from product where (select EXTRACT(MONTH FROM  expired_date)) =
(select  EXTRACT(MONTH FROM ( current_date + interval '1 month')));
--4. Написать запрос, который выводит самый дорогой продукт.
select name, price from product where price = (select max(price) from product);
--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select t.name, count(p.name) from product p inner join type t on p.type_id=t.id group by t.name;
--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product p inner join type t on p.type_id=t.id where t.name = 'СЫР' or t.name = 'МОЛОКО';
--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.name, count(p.name) from product p inner join type t on p.type_id=t.id group by t.name having count(p.name) < 3;
--8. Вывести все продукты и их тип.
select p.name, t.name from product p inner join type t on p.type_id=t.id;