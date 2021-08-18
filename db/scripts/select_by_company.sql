insert into company(id, name) values(1, 'engineering');
insert into company(id, name) values(2, 'service');
insert into company(id, name) values(3, 'technik');
insert into company(id, name) values(4, 'service quality');
insert into company(id, name) values(5, 'technical support');

insert into person(id, name, company_id) values(1, 'Алексей', 1);
insert into person(id, name, company_id) values(2, 'Иван', 1);
insert into person(id, name, company_id) values(3, 'Игорь', 1);
insert into person(id, name, company_id) values(4, 'Павел', 3);
insert into person(id, name, company_id) values(5, 'Антон', 3);
insert into person(id, name, company_id) values(6, 'Дмитрий', 4);
insert into person(id, name, company_id) values(7, 'Александр', 5);
insert into person(id, name, company_id) values(8, 'Егор', 5);
insert into person(id, name, company_id) values(9, 'Евгений', 5);
insert into person(id, name, company_id) values(10, 'Сергей', 5);

/* В одном запросе получить имена всех person, которые не состоят в компании с id = 5
и название компании для каждого человека */

select p.id, p.name, c.name from person p join company c on p.company_id = c.id where company_id != 5;

/* Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании */

select c.name, count(*) as persons_count
from person p join company c on p.company_id = c.id
group by c.name
having count(*) = (select max(persons_count)
				   from (select c.name, count(*) as persons_count
						 from person p join company c on p.company_id = c.id
						 group by c.name)t1);