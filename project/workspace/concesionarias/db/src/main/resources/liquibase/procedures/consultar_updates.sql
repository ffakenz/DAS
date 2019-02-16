CREATE PROCEDURE consultar_updates (
    @tipos_de_planes           VARCHAR(100)
    , @offset_from             DATETIME
    , @offset_to               DATETIME
) AS
SELECT *
FROM notification_updates
WHERE cuota_fecha_pago BETWEEN @offset_from AND @offset_to
    AND plan_tipo_de_plan = @tipos_de_planes
ORDER BY plan_id, plan_fecha_ultima_actualizacion, cuota_nro_cuota ASC


-- EXAMPLES
-- exec consultar_updates '2018-01-08 20:58:00'; -- 7 { ALL }
-- exec consultar_updates '2018-02-08 20:58:00'; -- 6 { CUOTA 1 FROM PLAN 1 IS EXLUDED }
-- exec consultar_updates '2018-02-09 00:00:00'; -- 5 { PLAN 2 IS EXCLUDED WITH ITS CUOTA AND CUOTA 1 FROM PLAN 1 }