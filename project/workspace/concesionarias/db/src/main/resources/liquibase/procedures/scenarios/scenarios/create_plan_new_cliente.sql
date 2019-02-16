CREATE PROCEDURE create_plan_new_cliente (
    @documento                      BIGINT
    , @nombre                       VARCHAR(100)
    , @apellido                     VARCHAR(100)
    , @nro_telefono                 VARCHAR(20)
    , @email                        VARCHAR(50)
    , @fecha_alta                   DATETIME
    , @vehiculo                     BIGINT
    , @tipo_de_plan                 VARCHAR(100) = 'GOB'
)
AS
BEGIN

    INSERT INTO dbo.clientes (documento, nombre, apellido, nro_telefono, email, fecha_de_alta)
    VALUES (@documento, @nombre, @apellido, @nro_telefono, @email, @fecha_alta)

    DECLARE @id_cliente BIGINT
    SELECT TOP 1 @id_cliente = id_cliente
    FROM clientes
    WHERE documento = @documento
    ORDER BY id_cliente DESC

    INSERT INTO dbo.planes (cliente, vehiculo, tipo_de_plan, estado, fecha_alta, fecha_ultima_actualizacion)
    VALUES (@id_cliente, @vehiculo, @tipo_de_plan, 'en_proceso', @fecha_alta, @fecha_alta)

    DECLARE @plan_id BIGINT
    SELECT TOP 1 @plan_id = id
    FROM planes
    WHERE   cliente        = @id_cliente
        AND vehiculo       = @vehiculo
        AND fecha_alta     = @fecha_alta
        AND tipo_de_plan   = @tipo_de_plan
    ORDER BY id DESC

    -- creating 60 cuotas for the client
    DECLARE @cnt INT = 1
    WHILE @cnt <= 60
    BEGIN
       INSERT INTO dbo.cuotas (id_plan, nro_cuota, fecha_alta)
       VALUES (@plan_id, @cnt, DATEADD(MONTH, @cnt - 1, @fecha_alta))

       SET @cnt = @cnt + 1
    END
END