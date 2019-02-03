CREATE PROCEDURE get_participantes (
    @mes_sorteo INT
) AS
WITH concesionarias_participantes AS (
    SELECT c.id
    FROM concesionaria c
        INNER JOIN consumo jc ON jc.concesionaria = c.id
    WHERE c.codigo IS NOT NULL -- concesionaria aprobada
        -- concesionaria consumida en los ultimos 5 dias
        AND js.estado = 'success'
        AND js.offset > (GATEDATE() - 5)
        AND DATE_PART(js.offset, MONTH()) <= @mes_sorteo
    ORDER BY id DESC;
), estado_cuentas_participantes AS (
    SELECT
        c.id    AS concesionaria_id,
        ec.id   AS estado_cuenta_id,
        COUNT(cu) AS total_cuotas,
        SUM(
            CASE WHEN
                cu.fecha_pago IS NOT NULL
                AND DATE_PART(cu.fecha_pago, MONTH())  <= @mes_sorteo
                AND cu.nro_cuota < 36
            THEN 1 ELSE 0
        ) AS total_cuotas_paagas
    FROM concesionarias_participantes c
        INNER JOIN estado_cuentas ec ON ec.concesionaria = c.id
        INNER JOIN cuotas cu ON estado_cuenta_id = ec.id
    GROUP BY c.id, ec.id
)
SELECT *
FROM estado_cuentas ec
    INNER JOIN estado_cuentas_participantes ecp
        ON ec.id = ecp.estado_cuenta_id
WHERE ecp.total_cuotas = total_cuotas_paagas -- todas las cuotas al dia
ORDER BY id DESC;
