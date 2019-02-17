CREATE PROCEDURE get_ultimo_ganador AS
SELECT TOP 1 *
FROM participantes
WHERE estado = 'ganador'
ORDER BY id_sorteo DESC;
