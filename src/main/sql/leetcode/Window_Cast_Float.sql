--Restaurant Growth
--Table: Customer
--
--+---------------+---------+
--| Column Name   | Type    |
--+---------------+---------+
--| customer_id   | int     |
--| name          | varchar |
--| visited_on    | date    |
--| amount        | int     |
--+---------------+---------+
--(customer_id, visited_on) is the primary key for this table.
--This table contains data about customer transactions in a restaurant.
--visited_on is the date on which the customer with ID (customer_id) have visited the restaurant.
--amount is the total paid by a customer.
--
--
--You are the restaurant owner and you want to analyze a possible expansion (there will be at least one customer every day).
--
--Write an SQL query to compute moving average of how much customer paid in a 7 days window (current day + 6 days before) .
--
--The query result format is in the following example:
--
--Return result table ordered by visited_on.
--
--average_amount should be rounded to 2 decimal places, all dates are in the format ('YYYY-MM-DD').
--
--
--
--Customer table:
--+-------------+--------------+--------------+-------------+
--| customer_id | name         | visited_on   | amount      |
--+-------------+--------------+--------------+-------------+
--| 1           | Jhon         | 2019-01-01   | 100         |
--| 2           | Daniel       | 2019-01-02   | 110         |
--| 3           | Jade         | 2019-01-03   | 120         |
--| 4           | Khaled       | 2019-01-04   | 130         |
--| 5           | Winston      | 2019-01-05   | 110         |
--| 6           | Elvis        | 2019-01-06   | 140         |
--| 7           | Anna         | 2019-01-07   | 150         |
--| 8           | Maria        | 2019-01-08   | 80          |
--| 9           | Jaze         | 2019-01-09   | 110         |
--| 1           | Jhon         | 2019-01-10   | 130         |
--| 3           | Jade         | 2019-01-10   | 150         |
--+-------------+--------------+--------------+-------------+
--
--Result table:
--+--------------+--------------+----------------+
--| visited_on   | amount       | average_amount |
--+--------------+--------------+----------------+
--| 2019-01-07   | 860          | 122.86         |
--| 2019-01-08   | 840          | 120            |
--| 2019-01-09   | 840          | 120            |
--| 2019-01-10   | 1000         | 142.86         |
--+--------------+--------------+----------------+
--
--1st moving average from 2019-01-01 to 2019-01-07 has an average_amount of (100 + 110 + 120 + 130 + 110 + 140 + 150)/7 = 122.86
--2nd moving average from 2019-01-02 to 2019-01-08 has an average_amount of (110 + 120 + 130 + 110 + 140 + 150 + 80)/7 = 120
--3rd moving average from 2019-01-03 to 2019-01-09 has an average_amount of (120 + 130 + 110 + 140 + 150 + 80 + 110)/7 = 120
--4th moving average from 2019-01-04 to 2019-01-10 has an average_amount of (130 + 110 + 140 + 150 + 80 + 110 + 130 + 150)/7 = 142.86


/* Write your T-SQL query statement below */
SELECT
    b.visited_on,
    b.amount,
    b.average_amount
FROM
    (SELECT
        a.visited_on,
        SUM(a.amount) OVER (ORDER BY a.visited_on  ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) as amount,
        ROUND(SUM(a.amount) OVER (ORDER BY a.visited_on  ROWS BETWEEN 6 PRECEDING AND CURRENT ROW)/CAST(7 AS float),2) as average_amount
    FROM
        (SELECT visited_on,SUM(amount) AS amount FROM Customer GROUP BY visited_on) a) b
WHERE b.visited_on>=(SELECT DATEADD(day,6,MIN(visited_on)) FROM Customer)


/*Group By on column and aggregate result in window function*/
SELECT
    visited_on,
    amount,
    average_amount
FROM
    (SELECT
        visited_on,
        SUM(SUM(amount)) OVER (ORDER BY visited_on ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) AS amount,
        ROUND(AVG(SUM(amount)) OVER (ORDER BY visited_on ROWS BETWEEN 6 PRECEDING AND CURRENT ROW),2) AS average_amount,
        MIN(visited_on) OVER () AS min_date
    FROM
        Customer
    GROUP BY visited_on) a
WHERE DATEDIFF(visited_on,min_date)>=6
ORDER BY visited_on