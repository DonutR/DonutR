--How to fetch 3rd highest salary using Rank Function?
select * from (
    select dense_rank() over ( order by  salary desc) as Rnk,E.* from Employee E)
    where Rnk=3;

