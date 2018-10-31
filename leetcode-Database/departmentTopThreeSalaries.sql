# 185. Department Top Three Salaries
#   Write a SQL query to find employees who earn the top three salaries in each of the department.
#   The Employee table holds all employees. 
#     Every employee has an Id, and there is also a column for the department Id.
#            +----+-------+--------+--------------+
#            | Id | Name  | Salary | DepartmentId |
#            +----+-------+--------+--------------+
#            | 1  | Joe   | 70000  | 1            |
#            | 2  | Henry | 80000  | 2            |
#            | 3  | Sam   | 60000  | 2            |
#            | 4  | Max   | 90000  | 1            |
#            | 5  | Janet | 69000  | 1            |
#            | 6  | Randy | 85000  | 1            |
#            +----+-------+--------+--------------+
#    The Department table holds all departments of the company.
#            +----+----------+
#            | Id | Name     |
#            +----+----------+
#            | 1  | IT       |
#            | 2  | Sales    |
#            +----+----------+
#    Hint: A top 3 salary in this company means there is no more than 3 salary bigger than itself in the company.

select d.Name as Department, e1.Name as Employee, e1.Salary
from Employee e1, Department d
where e1.DepartmentId = d.Id
and 3 > (
    select count(distinct e2.Salary)
    from Employee e2
    where e1.Salary < e2.Salary
    and e1.DepartmentId = e2.DepartmentId
) 

