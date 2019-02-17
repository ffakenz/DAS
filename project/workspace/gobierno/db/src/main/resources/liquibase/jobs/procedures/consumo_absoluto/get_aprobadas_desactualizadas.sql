CREATE PROCEDURE get_aprobadas_desactualizadas (
    @days INT
) AS
WITH aprobadas AS (
    -- TODO: Check how to replace this with EXEC get_aprobadas
    SELECT *
    FROM concesionaria
    WHERE fecha_alta IS NOT NULL
      AND codigo IS NOT NULL
), concesionarias_actualizadas AS (
    SELECT DISTINCT c.id_concesionaria
    FROM consumo c
        JOIN job_consumo jc ON c.id_job_consumo = jc.id
    WHERE c.estado = 'success' -- consumo exitoso
        AND jc.fecha >= DATEADD(DAY, @days, GETDATE()) -- fecha considerada como actualizacion
), concesionarias_desactualizadas AS (
    SELECT *
    FROM aprobadas a
    WHERE a.id NOT IN (
        SELECT c.id_concesionaria
        FROM concesionarias_actualizadas c
    )
)
SELECT *
FROM concesionarias_desactualizadas;
