CREATE PROCEDURE q_estado_cuentas_dash AS
SELECT *
FROM estado_cuentas_dash
ORDER BY documento_cliente, nro_plan_concesionaria DESC