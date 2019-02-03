CREATE PROCEDURE actualizar_fecha_sorteo (
    @id_sorteo          BIGINT
    , @fecha_ejecucion  DATETIME
) AS
UPDATE sorteos
SET fecha_ejecucion = @fecha_ejecucion,
    mes_sorteo = DATEPART(MONTH, @fecha_ejecucion)
WHERE id = @id_sorteo;