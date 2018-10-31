# 184. Department Highest Salary
#   Write a SQL query to find employees who have the highest salary in each of the departments.

# Method 1:
    select d.Name as Department, e1.Name as Employee, e1.Salary
    from Employee e1, Department d, (select max(e2.Salary) as maxs, e2.DepartmentId 
                                      from Employee e2 
                                      group by e2.DepartmentId ) as ms
    where e1.DepartmentId = d.Id
    and e1.Salary = ms.maxs and e1.DepartmentId = ms.DepartmentId

# Method 2:
      select d.Name as Department, e1.Name as Employee, e1.Salary
      from Employee e1, Department d
      where e1.DepartmentId = d.Id
      and e1.Salary = (
          select max( e2.Salary)
          from Employee e2
          where e1.DepartmentId = e2.DepartmentId
          group by e2.DepartmentId
      )
