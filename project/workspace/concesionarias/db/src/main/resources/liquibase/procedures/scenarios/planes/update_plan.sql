CREATE PROCEDURE update_plan (
    @id                        BIGINT
    , @estado                        VARCHAR(100)
    , @fecha_ultima_actualizacion    DATETIME
)
AS
UPDATE dbo.planes
   SET estado                     = @estado
    ,  fecha_ultima_actualizacion = @fecha_ultima_actualizacion
WHERE id = @id