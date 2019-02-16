CREATE PROCEDURE insert_cuota (
    @id_plan             BIGINT
    , @nro_cuota         BIGINT
    , @monto             INT
    , @fecha_pago        DATETIME
    , @fecha_alta        DATETIME
)
AS
INSERT INTO dbo.cuotas (id_plan, nro_cuota, monto, fecha_pago, fecha_alta)
VALUES (@id_plan, @nro_cuota, @monto, @fecha_pago, @fecha_alta)