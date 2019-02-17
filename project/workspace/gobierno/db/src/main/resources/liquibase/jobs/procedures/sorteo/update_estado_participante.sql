CREATE PROCEDURE update_estado_participante (
    @id_sorteo BIGINT
    , @id_plan BIGINT
    , @id_concesionaria BIGINT
    , @id_consumer BIGINT
    , @id_vehiculo BIGINT
    , @estado VARCHAR(100)
) AS
UPDATE participantes
SET estado = @estado
WHERE id_sorteo = @id_sorteo
  AND id_plan = @id_plan
  AND id_concesionaria = @id_concesionaria
  AND id_consumer = @id_consumer
  AND id_vehiculo = @id_vehiculo;