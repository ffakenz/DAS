CREATE PROCEDURE update_sorteo_fecha_ejecucion (
    @id_sorteo  BIGINT
    , @fecha_ejecucion   DATE
) AS
UPDATE sorteos
SET fecha_ejecucion = @fecha_ejecucion
WHERE id = @id_sorteo;