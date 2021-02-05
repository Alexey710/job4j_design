--draft;
CREATE TABLE company (
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);
insert into company (id, name) values (1, 'company1');
insert into company (id, name) values (2, 'company2');
insert into company (id, name) values (3, 'company3');
insert into company (id, name) values (5, 'company5');


CREATE TABLE person (
    id integer NOT NULL,
    name character varying,
    company_id integer,
    CONSTRAINT person_pkey PRIMARY KEY (id)
);
insert into person (id, name, company_id) values (10, 'person1', 1);
insert into person (id, name, company_id) values (11, 'person2', 1);
insert into person (id, name, company_id) values (12, 'person3', 1);
insert into person (id, name, company_id) values (13, 'person4', 2);
insert into person (id, name, company_id) values (14, 'person5', 3);
insert into person (id, name, company_id) values (15, 'person6', 5);


select p.name, c.name from person p join company c on p.company_id=c.id where c.id <> 5;

select * from (select c.name, count(p.id) as sum from person p join company c on p.company_id=c.id group by c.name) sub_query1
 where sum = (
 select max(sum) from (select c.name, count(p.id) as sum from person p join company c on p.company_id=c.id group by c.name)
sub_query2
);