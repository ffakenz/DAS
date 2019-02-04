CREATE PROCEDURE get_concesionarias_notificadas (
    @idSorteo BIGINT
) AS
SELECT *
FROM concesionarias_notificadas
WHERE id_sorteo = @idSorteo