Exclusive OR (XOR). Show the countries that are big by area (more than 3 million) or big by population (more than 250 million) but not both.
Show name, population and area.

Australia has a big area but a small population, it should be included.
Indonesia has a big population but a small area, it should be included.
China has a big population and big area, it should be excluded.
United Kingdom has a small population and a small area, it should be excluded.

select name,population,area from world where
(population >=250000000 and area <3000000) or
(population <250000000 and area >=3000000)


select name,population,area  from (
       select
        *,
        case when population>=250000000 and area< 3000000 then 1
        when population < 250000000 and area>=3000000 then 1
        else null
        end as XORCOMBO
        from world
                 )X
where XORCOMBO is not null

SELECT name FROM (
SELECT a.name,CASE WHEN a.population> b.population THEN TRUE ELSE FALSE END as comp
(SELECT name,population FROM world) a
JOIN
(SELECT population FROM world WHERE name='Russia') b) c
WHERE c.comp=TRUE;