-- Write your PostgreSQL query statement below
SELECT emp.name, bs.bonus
FROM Employee emp LEFT JOIN Bonus bs
ON emp.empId = bs.empId
WHERE bs.bonus < 1000 OR bs.bonus is null;