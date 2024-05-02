/*

game
id	mdate	stadium	team1	team2
1001	8 June 2012	National Stadium, Warsaw	POL	GRE
1002	8 June 2012	Stadion Miejski (Wroclaw)	RUS	CZE
1003	12 June 2012	Stadion Miejski (Wroclaw)	GRE	CZE
1004	12 June 2012	National Stadium, Warsaw	POL	RUS
...

goal
matchid	teamid	player	gtime
1001	POL	Robert Lewandowski	17
1001	GRE	Dimitris Salpingidis	51
1002	RUS	Alan Dzagoev	15
1002	RUS	Roman Pavlyuchenko	82
...

eteam
id	teamname	coach
POL	Poland	Franciszek Smuda
RUS	Russia	Dick Advocaat
CZE	Czech Republic	Michal Bilek
GRE	Greece	Fernando Santos
...


List every match with the goals scored by each team as shown. This will use "CASE WHEN" which has not been explained in any previous exercises.
mdate	team1	score1	team2	score2
1 July 2012	ESP	4	ITA	0
10 June 2012	ESP	1	ITA	1
10 June 2012	IRL	1	CRO	3
...
Notice in the query given every goal is listed. If it was a team1 goal then a 1 appears in score1, otherwise there is a 0. You could SUM this column to get a count of the goals scored by team1. Sort your result by mdate, matchid, team1 and team2.*/

SELECT mdate,team1,SUM(score1) as score1,team2,SUM(score2) as score2
FROM (
SELECT mdate,id,
team1,
CASE WHEN team1=teamid THEN 1 ELSE 0 END as score1,
team2,
CASE WHEN team2=teamid THEN 1 ELSE 0 END as score2,
matchid
FROM
(SELECT mdate,id,team1,team2 FROM game) a
LEFT OUTER JOIN
goal b
ON a.id=b.matchid) c
GROUP BY mdate,team1,team2
ORDER BY mdate, matchid, team1, team2