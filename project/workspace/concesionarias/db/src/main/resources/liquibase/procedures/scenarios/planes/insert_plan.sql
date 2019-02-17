CREATE PROCEDURE insert_plan (
    @cliente                        BIGINT
    , @vehiculo                      BIGINT
    , @tipo_de_plan                  VARCHAR(100)
    , @estado                        VARCHAR(100)
    , @fecha_alta                    DATETIME
    , @fecha_ultima_actualizacion    DATETIME
)
AS
BEGIN
    INSERT INTO dbo.planes (cliente, vehiculo, tipo_de_plan, estado, fecha_alta, fecha_ultima_actualizacion)
    VALUES (@cliente, @vehiculo, @tipo_de_plan, @estado, @fecha_alta, @fecha_ultima_actualizacion)
END