--Median Employee Salary
--The Employee table holds all employees. The employee table has three columns: Employee Id, Company Name, and Salary.
--
--+-----+------------+--------+
--|Id   | Company    | Salary |
--+-----+------------+--------+
--|1    | A          | 2341   |
--|2    | A          | 341    |
--|3    | A          | 15     |
--|4    | A          | 15314  |
--|5    | A          | 451    |
--|6    | A          | 513    |
--|7    | B          | 15     |
--|8    | B          | 13     |
--|9    | B          | 1154   |
--|10   | B          | 1345   |
--|11   | B          | 1221   |
--|12   | B          | 234    |
--|13   | C          | 2345   |
--|14   | C          | 2645   |
--|15   | C          | 2645   |
--|16   | C          | 2652   |
--|17   | C          | 65     |
--+-----+------------+--------+
--Write a SQL query to find the median salary of each company. Bonus points if you can solve it without using any built-in SQL functions.
--
--+-----+------------+--------+
--|Id   | Company    | Salary |
--+-----+------------+--------+
--|5    | A          | 451    |
--|6    | A          | 513    |
--|12   | B          | 234    |
--|9    | B          | 1154   |
--|14   | C          | 2645   |
--+-----+------------+--------+

SELECT
    a.id,
    a.company,
    a.salary
FROM
    (SELECT
        Id,
        Company,
        Salary,
        COUNT(Salary) OVER (PARTITION BY Company) as ct
    FROM Employee ) a
    JOIN
    (SELECT
        Id,
        Company,
        Salary,
        ROW_NUMBER() OVER (PARTITION BY Company ORDER BY Salary) as rn
    FROM Employee ) b
    ON a.id=b.id AND a.Company=b.Company
WHERE
(a.ct%2=0 AND (b.rn=a.ct/2 OR b.rn=a.ct/2+1))
OR
(a.ct %2 <> 0 AND b.rn =a.ct/2+1)