CREATE PROCEDURE get_consumo_absoluto_by_cid_and_sid (
    @concesionariaId  BIGINT
    , @idSorteo BIGINT
) AS
SELECT *
FROM consumo_absoluto
WHERE concesionaria_id = @concesionariaId
  AND id_sorteo = @idSorteo;
