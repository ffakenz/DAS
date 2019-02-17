CREATE PROCEDURE update_estado_sorteo (
    @id_sorteo  BIGINT
    , @estado   VARCHAR(100)
) AS
UPDATE sorteos
SET estado = @estado
WHERE id = @id_sorteo;