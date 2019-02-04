CREATE PROCEDURE log_ejecuciones_sorteo (
    @idSorteo  BIGINT
    , @estado   VARCHAR(100)
    , @comments VARCHAR(2000)
) AS
INSERT INTO ejecuciones_sorteo(id_sorteo, estado, comments)
VALUES (@idSorteo, @estado, @comments);