CREATE PROCEDURE log_cuota (
    @nro_cuota BIGINT
    , @estado_cuenta_id BIGINT
    , @fecha_alta_concesionaria DATETIME
    , @fecha_vencimiento DATETIME
    , @monto INT
    , @fecha_pago DATETIME
) AS
INSERT INTO cuotas(nro_cuota, estado_cuenta_id, fecha_alta_concesionaria, fecha_vencimiento, monto, fecha_pago)
VALUES (@nro_cuota, @estado_cuenta_id, @fecha_alta_concesionaria, @fecha_vencimiento, @monto, @fecha_pago);