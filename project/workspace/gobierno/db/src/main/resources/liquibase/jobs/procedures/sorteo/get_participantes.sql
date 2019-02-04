CREATE PROCEDURE get_participantes (
    @days INT,
    @cuotas_min INT,
    @cuotas_max INT
) AS
WITH estado_cuentas_participantes AS (
    SELECT
        c.id    AS concesionaria_id,
        ec.id   AS estado_cuenta_id,
        SUM(CASE WHEN cu.fecha_pago IS NOT NULL THEN 1 ELSE 0 END) AS total_cuotas_pagas
    FROM
        concesionaria c
        INNER JOIN estado_cuentas ec
            ON ec.concesionaria = c.id AND c.codigo IS NOT NULL -- concesionaria aprobada
        INNER JOIN cuotas cu
            ON estado_cuenta_id = ec.id
    WHERE
        ec.fecha_ultima_actualizacion >= DATEADD(DAY, @days, GETDATE())
    GROUP BY c.id, ec.id
), participantes_after_balance AS (
    SELECT *
    FROM estado_cuentas_participantes
    WHERE total_cuotas_pagas BETWEEN @cuotas_min AND @cuotas_max
)
SELECT *
FROM participantes_after_balance;