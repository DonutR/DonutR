--List the films together with the leading star for all 1962 films.
--
--movie
--id	title	yr	director	budget	gross
--actor
--id	name
--casting
--movieid	actorid	ord

--JOIN predicate push down example both the below queries gives same answer but first one will be performing good,

select a.title,c.name from
movie a
join
casting b
join
actor c
on a.id=b.movieid and b.actorid=c.id and a.yr= 1962 and b.ord=1

select a.title,c.name from
movie a
join
casting b
join
actor c
on a.id=b.movieid and b.actorid=c.id
where a.yr= 1962 and b.ord=1

select a.title,c.name from
movie a
join
casting b
on a.id=b.movieid and a.yr= 1962
join
actor c
on b.actorid=c.id and b.ord=1

select a.title,c.name from
(select * from movie where yr= 1962) a
join
(select * from casting where ord=1) b
on a.id=b.movieid
join
actor c
on b.actorid=c.id