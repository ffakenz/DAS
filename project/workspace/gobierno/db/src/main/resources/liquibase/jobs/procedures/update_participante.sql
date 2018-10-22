CREATE PROCEDURE update_participante (
    @id_sorteo BIGINT
    , @id_plan BIGINT
    , @estado BIGINT
) AS
UPDATE participantes
SET estado = @estado
WHERE id_sorteo = @id_sorteo
  AND id_plan = @id_plan;



