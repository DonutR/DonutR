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

--Find the routes involving two buses that can go from Craiglockhart to Lochend.
--Show the bus no. and company for the first bus, the name of the stop for the transfer,
--and the bus no. and company for the second bus.
--Self-join twice to find buses that visit Craiglockhart and Lochend, then join those on matching stops.

select c.num,c.company,stope.name,d.num,d.company from
(select a.company,a.num,b.stop
from route a join route b
on a.company=b.company and a.num=b.num
join stops stopa on (a.stop=stopa.id)
where stopa.name='Craiglockhart') c
join
(select b.company,b.num,a.stop
from route a join route b
on a.company=b.company and a.num=b.num
join stops stopb on (b.stop=stopb.id)
where stopb.name='Lochend') d
on c.stop=d.stop
join stops stope on (c.stop=stope.id)
order by  c.num,c.company,stope.name,d.num,d.company
