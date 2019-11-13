/*
Employees Earning More Than Their Managers

The Employee table holds all employees including their managers. Every employee has an Id, 
and there is also a column for the manager Id.

+----+-------+--------+-----------+
| Id | Name  | Salary | ManagerId |
+----+-------+--------+-----------+
| 1  | Joe   | 70000  | 3         |
| 2  | Henry | 80000  | 4         |
| 3  | Sam   | 60000  | NULL      |
| 4  | Max   | 90000  | NULL      |
+----+-------+--------+-----------+
Given the Employee table, write a SQL query that finds out employees who earn more than their
managers. For the above table, Joe is the only employee who earns more than his manager.

+----------+
| Employee |
+----------+
| Joe      |
+----------+

https://leetcode.com/problems/employees-earning-more-than-their-managers/
*/

## Without join:
# select Em1.Name as Employee
# from Employee Em1, Employee Em2
# where Em1.Salary > Em2.Salary and Em1.ManagerId = Em2.Id

select Em1.Name as Employee
from Employee Em1 join Employee Em2
on (Em1.Salary > Em2.Salary and Em1.ManagerId = Em2.Id)

