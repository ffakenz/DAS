CREATE PROCEDURE get_participantes_sorteo (
    @id_sorteo BIGINT
) AS
SELECT *
FROM participantes
WHERE id_sorteo = @id_sorteo;