CREATE PROCEDURE log_job_consumo (
    @fecha DATETIME = NULL
) AS
INSERT INTO job_consumo(fecha)
VALUES(ISNULL(@fecha, GETDATE()));

-- usage  : EXEC log_job_consumo '2018-01-04 23:43:55';
-- verify : SELECT * FROM job_consumo;