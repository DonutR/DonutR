--Execute the self join shown and observe that b.stop gives all the places you can get to from Craiglockhart, without changing routes.
--Change the query so that it shows the services from Craiglockhart to London Road.
--stops
--This is a list of areas served by buses. The detail does not really include each actual bus stop - just areas within Edinburgh and whole towns near Edinburgh.
--
--Field	Type	Notes
--id	INTEGER	Arbitrary value
--name	CHAR(30)	The name of an area served by at least one bus
--route
--A route is the path through town taken by a bus.
--
--Field	Type	Notes
--num	CHAR(5)	The number of the bus - as it appears on the front of the vehicle. Oddly these numbers often include letters
--company	CHAR(3)	Several bus companies operate in Edinburgh. The main one is Lothian Region Transport - LRT
--pos	INTEGER	This indicates the order of the stop within the route. Some routes may revisit a stop. Most buses go in both directions.
--stop	INTEGER	This references the stops table
--As different companies use numbers arbitrarily the num and the company are both required to identify a route.

select a.company, a.num, a.stop, b.stop
from route a join route b on
  (a.company=b.company and a.num=b.num)
where a.stop=53 and b.stop=149

--The query shown is similar to the previous one, however by joining two copies of the stops table we can refer to stops by name rather than by number.
--Change the query so that the services between 'Craiglockhart' and 'London Road' are shown.
--If you are tired of these places try 'Fairmilehead' against 'Tollcross'

select a.company, a.num, stopa.name, stopb.name
from route a join route b on
  (a.company=b.company and a.num=b.num)
  join stops stopa on (a.stop=stopa.id)
  join stops stopb on (b.stop=stopb.id)
where stopa.name='Craiglockhart' and stopb.name='London Road'

--Give a list of all the services which connect stops 115 and 137 ('Haymarket' and 'Leith')
select distinct a.company, a.num
from route a join route b on
  (a.company=b.company and a.num=b.num)
where (a.stop=115 and b.stop=137) or (b.stop=115 and a.stop=137)

--Give a distinct list of the stops which may be reached from 'Craiglockhart' by taking one bus, including 'Craiglockhart' itself, offered by the LRT company. Include the company and bus no. of the relevant services.
SELECT DISTINCT stopb.name,a.company,a.num
FROM route a JOIN route b
ON a.company='LRT' AND b.company='LRT' AND a.num=b.num
JOIN stops stopa ON (a.stop=stopa.id)
JOIN stops stopb ON (b.stop=stopb.id)
WHERE stopa.name='Craiglockhart'