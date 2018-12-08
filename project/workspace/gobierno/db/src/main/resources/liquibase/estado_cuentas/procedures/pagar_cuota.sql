CREATE PROCEDURE pagar_cuota (
    @nro_cuota BIGINT
    , @estado_cuenta_id BIGINT
    , @monto INT
    , @fecha_pago DATETIME
) AS
UPDATE cuotas
SET monto = @monto
    , fecha_pago = @fecha_pago
    , fecha_ultima_actualizacion = GETDATE()
WHERE
    nro_cuota = @nro_cuota
    AND estado_cuenta_id = @estado_cuenta_id
;