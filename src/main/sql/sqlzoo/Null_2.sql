--
--teacher
--id	dept	name	phone	mobile
--101	1	Shrivell	2753	07986 555 1234
--102	1	Throd	2754	07122 555 1920
--103	1	Splint	2293
--104		Spiregrain	3287
--105	2	Cutflower	3212	07996 555 6574
--106		Deadyawn	3345
--...
--dept
--id	name
--1	Computing
--2	Design
--3	Engineering
--...
--
--
--Use COUNT and GROUP BY dept.name to show each department and the number of staff. Use a RIGHT JOIN to ensure that the Engineering department is listed.

SELECT b.name,count(a.dept)
FROM
teacher a
RIGHT JOIN
dept b
ON a.dept=b.id
GROUP BY b.name
--
--Correct answer
--name	count(a.dept)
--Computing	3
--Design	1
--Engineering	0

--Note the INNER JOIN misses the teachers with no department and the departments with no teacher.

SELECT teacher.name, dept.name
 FROM teacher INNER JOIN dept
           ON (teacher.dept=dept.id)

--Correct answer
--name	name
--Shrivell	Computing
--Throd	Computing
--Splint	Computing
--Cutflower	Design
