CREATE PROCEDURE get_participantes (
    @cuotas_min INT,
    @cuotas_max INT,
    @fecha_creacion_sorteo DATE
) AS
WITH potenciales_participantes AS (
         SELECT
             c.id    AS id_concesionaria,
             ec.id   AS id_plan,
             ec.vehiculo AS id_vehiculo,
             cs.id AS id_consumer,
             cs.email AS email,
             SUM(CASE WHEN
                    cu.fecha_pago IS NOT NULL
                    AND cu.fecha_pago <= @fecha_creacion_sorteo
                THEN 1
                ELSE 0
             END) AS total_cuotas_pagas,
             SUM(CASE WHEN
                    cu.fecha_alta_concesionaria IS NOT NULL
                    AND cu.fecha_alta_concesionaria <= @fecha_creacion_sorteo
                THEN 1
                ELSE 0
             END) as cantidad_cuotas_emitidas
         FROM
             concesionaria c
             INNER JOIN estado_cuentas ec
                 ON ec.concesionaria = c.id AND c.codigo IS NOT NULL -- concesionaria aprobada
             INNER JOIN cuotas cu
                 ON estado_cuenta_id = ec.id
             INNER JOIN consumers cs
                ON cs.documento = ec.dni_consumer
         GROUP BY c.id, ec.id, ec.vehiculo, cs.id, cs.email
), participantes_after_balance AS (
    SELECT *
    FROM potenciales_participantes
    WHERE total_cuotas_pagas BETWEEN @cuotas_min AND @cuotas_max
        AND cantidad_cuotas_emitidas = total_cuotas_pagas
)
SELECT *
FROM participantes_after_balance;