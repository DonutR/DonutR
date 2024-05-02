
SELECT
        TOP 1 DATEADD(day,-5,'2019-06-30'),
        '2019-06-30'
    FROM
        Traffic

--Output
--{"headers": ["", ""], "values": [["2019-06-25 00:00:00", "2019-06-30"]]}
--Take care of this while using BETWEEN