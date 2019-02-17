CREATE PROCEDURE get_job_results_report
AS
SELECT *
FROM consumo_results
ORDER BY job_id, job_fecha_ejecucion, concesionaria_id, id_request_resp_consumo;
