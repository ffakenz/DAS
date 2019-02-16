CREATE PROCEDURE get_cuota (
    @id_plan        BIGINT
    , @nro_cuota    BIGINT
)
AS
SELECT TOP 1 *
FROM cuotas
WHERE id_plan = @id_plan
    AND nro_cuota = @nro_cuota