--List each country name where the population is larger than that of 'Russia'.

--Using IN CLAUSE
select name from world
  where population >
     (select population from world
      where name='Russia')

--Using Join
select name from (
select a.name,case when a.population> b.population then TRUE else FALSE end as comp
from
(select name,population from world) a
join
(select population from world where name='Russia') b) c
where c.comp=TRUE;