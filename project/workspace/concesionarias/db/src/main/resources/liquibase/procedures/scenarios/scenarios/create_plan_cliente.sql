CREATE PROCEDURE create_plan_cliente (
    @documento                      BIGINT
    , @fecha_alta                   DATETIME
    , @vehiculo                     BIGINT
    , @nro_cuota                    BIGINT
    , @tipo_de_plan                 VARCHAR(100) = 'GOB'
)
AS
BEGIN
    DECLARE @id_cliente BIGINT
    SELECT TOP 1 @id_cliente = id_cliente
    FROM clientes
    WHERE documento = @documento
    ORDER BY id_cliente DESC

    DECLARE @plan_id BIGINT
    SELECT TOP 1 @plan_id = id
    FROM planes
    WHERE   cliente        = @id_cliente
        AND vehiculo       = @vehiculo
        AND fecha_alta     = @fecha_alta
        AND tipo_de_plan   = @tipo_de_plan
    ORDER BY id DESC

    DECLARE @random BIGINT
    SET @random = FLOOR(RAND() * 99 + 1)
    IF(@random < 75)
    BEGIN
        -- paga en termino
        UPDATE dbo.cuotas
        SET fecha_pago = (
            SELECT TOP 1 new_date
            FROM dbo.date_in_range(
                cuotas.fecha_alta,
                cuotas.fecha_vencimiento
            )
        )
        WHERE cuotas.id_plan = @plan_id
        AND cuotas.nro_cuota = @nro_cuota
    END
    ELSE
    BEGIN
        -- paga fuera de termino
        DECLARE @delay BIGINT
        SET @delay = FLOOR(RAND() * 9 + 1)
        UPDATE dbo.cuotas
        SET fecha_pago = (
            SELECT TOP 1 new_date
            FROM dbo.date_in_range(
                cuotas.fecha_vencimiento,
                DATEADD(DAY, @delay, cuotas.fecha_vencimiento)
            )
        )
        WHERE cuotas.id_plan = @plan_id
        AND cuotas.nro_cuota = @nro_cuota
    END
END