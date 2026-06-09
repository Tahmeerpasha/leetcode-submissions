-- Write your PostgreSQL query statement below
SELECT wrld.name, wrld.population, wrld.area
FROM World wrld
WHERE wrld.area >= 3000000 OR wrld.population >= 25000000;