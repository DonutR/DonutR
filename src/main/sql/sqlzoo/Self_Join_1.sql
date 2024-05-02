--How Do you find all Employees with its managers?(Consider there is manager id also in Employee table)

Select e.employee_name as employee_name,m.employee_name as manager_name
from
Employee e
JOIN
Employee m
ON e.Manager_id=m.Employee_id;

