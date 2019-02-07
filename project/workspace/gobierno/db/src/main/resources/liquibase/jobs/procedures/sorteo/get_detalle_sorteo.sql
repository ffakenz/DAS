CREATE PROCEDURE get_detalle_sorteo(
    @sorteo_id BIGINT
) AS
SELECT
    s.id, s.estado, s.fecha_ejecucion, s.fecha_creacion
    , COUNT(DISTINCT id_plan) AS total_participantes
FROM sorteos s
    LEFT JOIN participantes p
        ON p.id_sorteo = s.id
WHERE s.id = @sorteo_id
GROUP BY s.id, s.estado, s.fecha_ejecucion, s.fecha_creacion
ORDER BY s.id, s.estado, s.fecha_ejecucion, s.fecha_creacion DESC;
