CREATE PROCEDURE update_sorteo (
    @id_sorteo  BIGINT
    , @estado   VARCHAR(100)
    , @fecha_ejecucion   DATE
) AS
UPDATE sorteos
SET estado = @estado
    , fecha_ejecucion = @fecha_ejecucion
WHERE id = @id_sorteo;