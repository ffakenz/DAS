CREATE PROCEDURE cancelar_plan (
    @tipo_de_plan   VARCHAR(100)
    , @plan_id      BIGINT
) AS
UPDATE planes
SET estado = 'cancelado'
    , fecha_ultima_actualizacion = GETDATE()
WHERE id = @plan_id
    AND tipo_de_plan = @tipo_de_plan;