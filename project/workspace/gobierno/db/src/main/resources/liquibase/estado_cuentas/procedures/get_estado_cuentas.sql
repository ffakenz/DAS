CREATE PROCEDURE get_estado_cuentas  AS
SELECT *
FROM estado_cuentas
ORDER BY id DESC;