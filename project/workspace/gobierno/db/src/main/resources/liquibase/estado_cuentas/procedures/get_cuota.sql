CREATE PROCEDURE get_cuota (
  @nro_cuota BIGINT
  , @estado_cuenta_id BIGINT
) AS
SELECT *
FROM cuotas
WHERE
    nro_cuota = @nro_cuota
    AND estado_cuenta_id = @estado_cuenta_id
;