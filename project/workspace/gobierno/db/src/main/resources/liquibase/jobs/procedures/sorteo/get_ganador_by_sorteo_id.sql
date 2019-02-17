CREATE PROCEDURE get_ganador_by_sorteo_id(
    @sorteo_id BIGINT
) AS
SELECT TOP 1 *
FROM participantes
WHERE estado = 'ganador'
    AND id_sorteo = @sorteo_id
ORDER BY id_sorteo DESC;
