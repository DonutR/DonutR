--You are given two tables: Students and Grades. Students contains three columns ID, Name and Marks.

--Ketty gives Eve a task to generate a report containing three columns: Name, Grade and Mark.
--Ketty doesn't want the NAMES of those students who received a grade lower than 8.
--The report must be in descending order by grade -- i.e. higher grades are entered first.
--If there is more than one student with the same grade (8-10) assigned to them, order those particular students by their name alphabetically.
--Finally, if the grade is lower than 8, use "NULL" as their name and list them by their grades in descending order.
--If there is more than one student with the same grade (1-7) assigned to them, order those particular students by their marks in ascending order.

--Students
----------
--Columns Type
--id  int
--name    String
--marks   int
--
--Grades
-------
--grade int
--min_mark    int
--max_mark    int

SELECT name,grade,marks
FROM
Students a
JOIN
Grades b
WHERE a.marks BETWEEN b.min_mark AND b.max_mark AND grade >=8
UNION ALL
SELECT NULL,grade,marks
FROM
Students a
JOIN
Grades b
WHERE a.marks BETWEEN b.min_mark AND b.max_mark AND grade <8
ORDER BY grade DESC,CASE WHEN name IS NULL THEN marks ELSE name END ASC
