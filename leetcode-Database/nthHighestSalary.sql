# 177. Nth Highest Salary
# Write a SQL query to get the nth highest salary from the Employee table.

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  set N = N - 1;
  RETURN (
      # Write your MySQL query statement below.
      select distinct Salary 
      from Employee
      order by Salary desc
      limit N, 1
  );
END

# Mysql - limit 语句语法：
#     select * from table limit [m],n;
# 其中，m—— [m]为可选，如果填写表示skip步长／记录开始的index（index从0开始），即跳过m条。
# n——显示条数。指从第m+1条记录开始，取n条记录。
#     eg: select * from tablename limit 2,4
#         即取出第3条至第6条，4条记录
