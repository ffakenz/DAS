CREATE PROCEDURE get_consumo_absoluto_by_plan_and_cid_and_sid (
    @concesionariaId  BIGINT
    , @planId BIGINT
    , @idSorteo BIGINT
) AS
SELECT TOP 1 *
FROM consumo_absoluto
WHERE concesionaria_id = @concesionariaId
  AND plan_id = @planId
  AND id_sorteo = @idSorteo;
