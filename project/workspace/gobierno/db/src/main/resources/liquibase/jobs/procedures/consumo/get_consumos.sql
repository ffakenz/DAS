CREATE PROCEDURE get_consumos AS
SELECT *
FROM consumo
ORDER BY id DESC;