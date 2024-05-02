--How to find count of duplicate rows?
Select rollno, count (rollno) from Student
Group by rollno
Having count (rollno)>1
Order by count (rollno) desc;



The HAVING clause filters group rows created by the GROUP BY clause.
The HAVING clause is applied to each group of the grouped table, much as a WHERE clause is applied to a select list.
If there is no GROUP BY clause, the HAVING clause is applied to the entire result as a single group.
The SELECT clause cannot refer directly to any column that does not have a GROUP BY clause.

---Any column referenced in a HAVING clause must be either a grouping column or a column that refers to the result of an aggregate function.
---In a HAVING clause, you cannot specify:
------An alias that was defined in the select list. You must repeat the original, unaliased expression.
------An ordinal number that refers to a select list item. Only the GROUP BY and ORDER BY clauses accept ordinal numbers