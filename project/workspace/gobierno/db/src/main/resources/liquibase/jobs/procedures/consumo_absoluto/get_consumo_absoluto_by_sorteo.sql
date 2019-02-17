CREATE PROCEDURE get_consumo_absoluto_by_sorteo (
    @idSorteo BIGINT
) AS
SELECT *
FROM consumo_absoluto
WHERE id_sorteo = @idSorteo;