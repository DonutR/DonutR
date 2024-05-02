--Project Employees III
--Table: Project
--
--+-------------+---------+
--| Column Name | Type    |
--+-------------+---------+
--| project_id  | int     |
--| employee_id | int     |
--+-------------+---------+
--(project_id, employee_id) is the primary key of this table.
--employee_id is a foreign key to Employee table.
--Table: Employee
--
--+------------------+---------+
--| Column Name      | Type    |
--+------------------+---------+
--| employee_id      | int     |
--| name             | varchar |
--| experience_years | int     |
--+------------------+---------+
--employee_id is the primary key of this table.
--
--
--Write an SQL query that reports the most experienced employees in each project. In case of a tie, report all employees with the maximum number of experience years.
--
--The query result format is in the following example:
--
--Project table:
--+-------------+-------------+
--| project_id  | employee_id |
--+-------------+-------------+
--| 1           | 1           |
--| 1           | 2           |
--| 1           | 3           |
--| 2           | 1           |
--| 2           | 4           |
--+-------------+-------------+
--
--Employee table:
--+-------------+--------+------------------+
--| employee_id | name   | experience_years |
--+-------------+--------+------------------+
--| 1           | Khaled | 3                |
--| 2           | Ali    | 2                |
--| 3           | John   | 3                |
--| 4           | Doe    | 2                |
--+-------------+--------+------------------+
--
--Result table:
--+-------------+---------------+
--| project_id  | employee_id   |
--+-------------+---------------+
--| 1           | 1             |
--| 1           | 3             |
--| 2           | 1             |
--+-------------+---------------+
--Both employees with id 1 and 3 have the most experience among the employees of the first project. For the second project, the employee with id 1 has the most experience.

--CORRECT IMPLEMENTATION LAST_VALUE
SELECT
    project_id,
    employee_id
FROM
    (SELECT
        a.project_id,
        a.employee_id,
        b.experience_years,
        LAST_VALUE(b.experience_years) OVER (PARTITION BY a.project_id ORDER BY b.experience_years ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) AS fist_val
    FROM
        Project a
        JOIN
        Employee b
    ON a.employee_id=b.employee_id) c
WHERE c.fist_val=c.experience_years

--INCORRECT IMPLEMENTATION LAST_VALUE
SELECT
    project_id,
    employee_id
FROM
    (SELECT
        a.project_id,
        a.employee_id,
        b.experience_years,
        LAST_VALUE(b.experience_years) OVER (PARTITION BY a.project_id ORDER BY b.experience_years) AS fist_val
    FROM
        Project a
        JOIN
        Employee b
    ON a.employee_id=b.employee_id) c
WHERE c.fist_val=c.experience_years

--CORRECT IMPLEMENTATION FIRST_VALUE
SELECT
    project_id,
    employee_id
FROM
    (SELECT
        a.project_id,
        a.employee_id,
        b.experience_years,
        FIRST_VALUE(b.experience_years) OVER (PARTITION BY a.project_id ORDER BY b.experience_years DESC) AS fist_val
    FROM
        Project a
        JOIN
        Employee b
    ON a.employee_id=b.employee_id) c
WHERE c.fist_val=c.experience_years
--CORRECT IMPLEMENTATION RANK
SELECT
    project_id,
    employee_id
FROM
    (SELECT
        a.project_id,
        a.employee_id,
        RANK() OVER (PARTITION BY a.project_id ORDER BY b.experience_years DESC) AS rnk
    FROM
        Project a
        JOIN
        Employee b
    ON a.employee_id=b.employee_id) c
WHERE c.rnk=1

--CORRECT IMPLEMENTATION MAX
SELECT
    project_id,
    employee_id
FROM
    (SELECT
        a.project_id,
        a.employee_id,
        b.experience_years,
        MAX(b.experience_years) OVER (PARTITION BY a.project_id) AS fist_val
    FROM
        Project a
        JOIN
        Employee b
    ON a.employee_id=b.employee_id) c
WHERE c.fist_val=c.experience_years