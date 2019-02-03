CREATE PROCEDURE log_sorteo (
    @fecha_ejecucion DATETIME
) AS
INSERT INTO sorteos(fecha_ejecucion)
VALUES(@fecha_ejecucion);