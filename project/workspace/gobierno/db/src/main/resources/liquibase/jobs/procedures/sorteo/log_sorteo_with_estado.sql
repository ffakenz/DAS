CREATE PROCEDURE log_sorteo_with_estado (
    @fecha_ejecucion DATE
    , @estado VARCHAR(100)
) AS
INSERT INTO sorteos(fecha_ejecucion, estado)
VALUES(@fecha_ejecucion, @estado);