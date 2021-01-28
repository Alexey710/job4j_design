create database draft;

create table departments (
    id serial primary key,
    name varchar(255)
);

create table emploees(
    id serial primary key,
    name varchar(255),
    departments_id int references departments(id)
);

insert into departments(name) values ('Department 1');
insert into departments(name) values ('Department 2');
insert into departments(name) values ('Department 3');
insert into departments(name) values ('Department 4');

insert into emploees(name, departments_id) values ('Emploee 1', 1);
insert into emploees(name, departments_id) values ('Emploee 2', 2);
insert into emploees(name, departments_id) values ('Emploee 3', 3);
insert into emploees(name, departments_id) values ('Emploee 4', 3);
insert into emploees(name, departments_id) values ('Emploee 5', 3);
insert into emploees(name, departments_id) values ('Emploee 6', 1);
insert into emploees(name) values ('Emploee 7');
insert into emploees(name) values ('Emploee 8');

--2. Выполнить запросы с left, rigth, full, cross соединениями
select * from departments d left join emploees e on d.id=e.departments_id;
select * from departments d right join emploees e on d.id=e.departments_id;
select * from departments d full join emploees e on d.id=e.departments_id;
select * from departments cross join emploees;
--3. Используя left join найти работников, которые не относятся ни к одну из департаментов
select * from emploees e left join departments d on d.id=e.departments_id where e.departments_id is null;
--4. Используя left и right join написать запросы, которые давали бы одинаковый результат.
select * from departments d left join emploees e on d.id=e.departments_id
where d.id=e.departments_id;
select * from departments d right join emploees e on d.id=e.departments_id
where d.id=e.departments_id;
--where e.departments_id=d.id;
--5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
--Используя cross join составить все возможные разнополые пары
create table teens (
    id serial primary key,
    name varchar(255),
    gender varchar(255)
);
INSERT INTO teens(name, gender)
VALUES ('Boy1', 'M'), ('Girl1', 'F'), ('Boy2', 'M'), ('Girl2', 'F'), ('Boy3', 'M'), ('Girl3', 'F');
select * from teens t1 cross join teens t2 where t1.gender='F' and t2.gender='M';
