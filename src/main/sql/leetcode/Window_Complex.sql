--Find Median Given Frequency of Numbers
--The Numbers table keeps the value of number and its frequency.
--
--+----------+-------------+
--|  Number  |  Frequency  |
--+----------+-------------|
--|  0       |  7          |
--|  1       |  1          |
--|  2       |  3          |
--|  3       |  1          |
--+----------+-------------+
--In this table, the numbers are 0, 0, 0, 0, 0, 0, 0, 1, 2, 2, 2, 3, so the median is (0 + 0) / 2 = 0.
--
--+--------+
--| median |
--+--------|
--| 0.0000 |
--+--------+
--Write a query to find the median of all numbers and name the result as median.

/* Write your T-SQL query statement below */
/*    AVG(number) as median */
SELECT
    AVG(CAST(number AS FLOAT)) as median
FROM
    (SELECT
        number,
        frequency,
        cumSum,
        sm,
        LAG(cumSum,1,1) OVER (ORDER BY number)+1 as lagTmp
    FROM
        (SELECT
            number,
            frequency,
            SUM(frequency) OVER (ORDER BY number) as cumSum,
            CAST(SUM(Frequency) OVER ()AS FLOAT) as sm
        FROM
            Numbers) a) b
WHERE
    (sm%2=0 AND FLOOR(sm/2)+1 BETWEEN lagTmp AND cumSum) OR
    (sm%2<>0 AND FLOOR(sm/2)+1 BETWEEN lagTmp AND cumSum OR FLOOR(sm/2) BETWEEN lagTmp AND cumSum)
