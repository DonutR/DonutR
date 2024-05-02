--movie
--id	title	yr	director	budget	gross
--actor
--id	name
--casting
--movieid	actorid	ord

--JOIN predicate push down example both the below queries gives same answer but first one will be performing good,

--List the film title and the leading actor for all of the films 'Julie Andrews' played in.
--
--Did you get "Little Miss Marker twice"?
--Julie Andrews starred in the 1980 remake of Little Miss Marker and not the original(1934).
--
--Title is not a unique field, create a table of IDs in your subquery


SELECT title,name FROM
movie a
JOIN
(SELECT DISTINCT(movieid) as movieid FROM casting WHERE actorid=(SELECT id FROM actor WHERE name='Julie Andrews')) b
JOIN
casting c
JOIN
actor d
ON a.id=b.movieid AND b.movieid =c.movieid AND c.actorid=d.id AND c.ord=1