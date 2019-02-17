CREATE PROCEDURE get_job_by_id (
    @job_id  BIGINT
) AS
SELECT TOP 1 *
FROM job_consumo
WHERE id = @job_id;
