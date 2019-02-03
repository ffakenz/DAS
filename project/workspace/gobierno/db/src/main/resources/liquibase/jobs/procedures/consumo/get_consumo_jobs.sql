CREATE PROCEDURE get_consumo_jobs AS
SELECT *
FROM job_consumo
ORDER BY id DESC;
