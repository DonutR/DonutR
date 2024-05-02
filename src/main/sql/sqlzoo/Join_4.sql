--List the films released in the year 1978 ordered by the number of actors in the cast, then by title.

--movie
--id	title	yr	director	budget	gross
--actor
--id	name
--casting
--movieid	actorid	ord

--JOIN predicate push down example both the below queries gives same answer but first one will be performing good,

select a.title as title,count(*) as ct
from
movie a
join
casting b
on a.id=b.movieid and a.yr= 1978
group by title
order by ct desc,title