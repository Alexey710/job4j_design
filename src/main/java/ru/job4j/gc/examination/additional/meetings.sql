--1. Есть таблица встреч(id, name), есть таблица юзеров(id, name).
--Нужно доработать модель базы данных, так чтобы пользователи могли учавствовать во встречах.
--У каждого участника встречи может быть разный статус участия - например подтвердил участие, отклонил.
--2. Нужно написать запрос, который получит список всех заявок и количество подтвердивших участников.
--3. Нужно получить все совещания, где не было ни одной заявки на посещения

-- db draft;
create  table meetings (id serial primary key, name text);
create  table users (id serial primary key, name text);
create table schedule (id serial primary key, status boolean,
user_id int references users(id), meetings_id int references meetings(id));
insert into meetings(name) values ('meetings1'), ('meetings2'), ('meetings3');
insert into users(name) values ('user1'), ('user2'), ('user3');
insert into schedule(status, user_id, meetings_id)
values ('TRUE', 1, 1), ('FALSE', 2, 1), ('TRUE', 1, 2);
--2. Нужно написать запрос, который получит список всех заявок и количество подтвердивших участников.
with schedule_users as
(select s.status, s.meetings_id, u.name from schedule s full outer join users u on s.user_id=u.id)
select (select count(status) from schedule where status = 'TRUE') as all_true,
su.status, su.name, m.name from schedule_users su
full outer join meetings m on m.id=su.meetings_id
where su.status is not null;
--3. Нужно получить все совещания, где не было ни одной заявки на посещения
with schedule_users as
(select s.status, s.meetings_id, u.name from schedule s full outer join users u on s.user_id=u.id)
select m.name from schedule_users su
full outer join meetings m on m.id=su.meetings_id
where su.status is null;



