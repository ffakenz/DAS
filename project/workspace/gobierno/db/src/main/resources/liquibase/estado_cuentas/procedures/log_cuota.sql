CREATE PROCEDURE log_cuota (
    @estado_cuenta_id BIGINT
    , @fecha_vencimiento DATETIME
    , @monto INT
    , @fecha_pago DATETIME
) AS
INSERT INTO cuotas(estado_cuenta_id,fecha_vencimiento,monto,fecha_pago)
VALUES (@estado_cuenta_id,@fecha_vencimiento,@monto,@fecha_pago);