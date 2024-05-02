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

--2. Select the code that shows the stops that are on route.num '2A' which can be reached with one bus from Haymarket?
SELECT S2.id, S2.name, R2.company, R2.num
  FROM stops S1, stops S2, route R1, route R2
 WHERE S1.name='Haymarket' AND S1.id=R1.stop
   AND R1.company=R2.company AND R1.num=R2.num
   AND R2.stop=S2.id AND R1.num='2A'


--https://www.w3schools.com/sql/sql_join_self.asp
--https://www.w3resource.com/sql/joins/cross-join.php