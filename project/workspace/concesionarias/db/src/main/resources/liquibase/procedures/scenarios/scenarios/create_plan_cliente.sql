CREATE PROCEDURE create_plan_cliente (
    @documento                    BIGINT
    , @fecha_alta                DATETIME
    , @vehiculo                     BIGINT
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


    UPDATE dbo.planes
    SET estado = 'en_proceso'
    WHERE id = @plan_id


    INSERT INTO dbo.cuotas (id_plan, nro_cuota, fecha_alta)
    VALUES (@plan_id, 1, @fecha_alta)
END