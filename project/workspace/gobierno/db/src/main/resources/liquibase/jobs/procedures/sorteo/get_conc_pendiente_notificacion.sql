CREATE PROCEDURE get_conc_pendiente_notificacion (
    @idSorteo BIGINT
) AS
SELECT *
FROM conc_pendiente_notificacion
WHERE id_sorteo = @idSorteo