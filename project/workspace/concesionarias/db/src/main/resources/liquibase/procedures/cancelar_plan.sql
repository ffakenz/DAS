CREATE PROCEDURE cancelar_plan (
    @plan_id BIGINT
) AS
UPDATE planes
SET cant_cuotas_pagas = 60
WHERE id = @plan_id;



