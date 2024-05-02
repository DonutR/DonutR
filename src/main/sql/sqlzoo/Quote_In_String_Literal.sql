--Find all details of the prize won by EUGENE O'NEILL

SELECT yr,subject,winner
FROM nobel
WHERE upper(winner)='EUGENE O\'NEILL'