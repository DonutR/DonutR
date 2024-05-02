SELECT name,gdp/
CASE WHEN population IS NULL THEN 1  WHEN population=0 THEN 1 ELSE population END  FROM world WHERE population>=200000000