--618. Students Report By Geography
--A U.S graduate school has students from Asia, Europe and America. The students' location information are stored in table student as below.
--
--
--| name   | continent |
--|--------|-----------|
--| Jack   | America   |
--| Pascal | Europe    |
--| Xi     | Asia      |
--| Jane   | America   |
--
--
--Pivot the continent column in this table so that each name is sorted alphabetically and displayed underneath its corresponding continent. The output headers should be America, Asia and Europe respectively. It is guaranteed that the student number from America is no less than either Asia or Europe.
--
--
--For the sample input, the output is:
--
--
--| America | Asia | Europe |
--|---------|------|--------|
--| Jack    | Xi   | Pascal |
--| Jane    |      |        |
--
--
--Follow-up: If it is unknown which continent has the most students, can you write a query to generate the student report?
--

SELECT am.name as America, CASE WHEN (asi.name IS NULL) THEN NULL ELSE asi.name END AS Asia,CASE WHEN (er.name IS NULL) THEN NULL ELSE er.name END AS Europe
FROM
(SELECT name,ROW_NUMBER() OVER (ORDER BY name) as rn FROM student WHERE continent='America') am
FULL JOIN
(SELECT name,ROW_NUMBER() OVER (ORDER BY name) as rn FROM student WHERE continent='Asia') asi
ON am.rn=asi.rn
FULL JOIN
(SELECT name,ROW_NUMBER() OVER (ORDER BY name) as rn FROM student WHERE continent='Europe') er
ON am.rn=er.rn