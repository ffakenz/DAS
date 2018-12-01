CREATE PROCEDURE cancelar_plan (
    @plan_id BIGINT
) AS
UPDATE planes
SET estado = 'cancelado'
WHERE id = @plan_id;



