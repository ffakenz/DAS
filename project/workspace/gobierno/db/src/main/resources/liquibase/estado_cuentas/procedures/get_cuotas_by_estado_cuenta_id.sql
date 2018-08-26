CREATE PROCEDURE get_cuotas_by_estado_cuenta_id (
   @estado_cuenta_id BIGINT
) AS
SELECT *
FROM cuotas
WHERE estado_cuenta_id = @estado_cuenta_id;