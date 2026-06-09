-- Write your PostgreSQL query statement below
SELECT cust.name
FROM Customer cust
WHERE cust.referee_id != 2 OR cust.referee_id is null;