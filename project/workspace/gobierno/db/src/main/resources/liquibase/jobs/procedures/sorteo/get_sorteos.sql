CREATE PROCEDURE get_sorteos AS
SELECT *
FROM sorteos
ORDER BY fecha_ejecucion DESC;
