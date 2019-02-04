CREATE PROCEDURE get_ejecuciones_sorteo AS
SELECT *
FROM ejecuciones_sorteo
ORDER BY id DESC;
