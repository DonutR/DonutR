--A median is defined as a number separating the higher half of a data set from the lower half. Query the median of the Northern Latitudes (LAT_N) from STATION and round your answer to  decimal places.
--
--Input Format
--
--The STATION table is described as follows
--
--Station
--========
--filed   type
--id      int
--city    String
--state    String
--lat_n   number
--lat_w   number
--
--where LAT_N is the northern latitude and LONG_W is the western longitude.

SELECT
    CASE WHEN (SELECT COUNT(*)%2 as id FROM STATION)<>0
    THEN ROUND(MAX(a.LAT_N),4,1)
    ELSE ROUND(SUM(a.LAT_N)/2,4,1)
    END
FROM
(SELECT LAT_N,ROW_NUMBER() OVER (ORDER BY LAT_N) as rnum
FROM STATION) a
JOIN
(SELECT COUNT(*)/2 as id FROM STATION) b
ON a.rnum=b.id OR a.rnum=b.id+1

SELECT
    CASE WHEN ct%2 <>0
    THEN ROUND(Country_Key,4,1)
    ELSE ROUND((Country_Key+ld)/2,4,1)
    END
FROM
    (SELECT
        Country_Key,
        LEAD(Country_Key) OVER(ORDER BY Country_Key) as ld,
        ROW_NUMBER() OVER (ORDER BY Country_Key) as rnum,
        COUNT(Country_Key) OVER( ) as ct
    FROM pub.VW_Dim_Country) a WHERE rnum=ct/2

