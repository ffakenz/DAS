CREATE PROCEDURE log_concesionarias_notificadas (
    @idSorteo BIGINT
    , @idConcesionaria BIGINT
) AS
INSERT INTO concesionarias_notificadas(id_sorteo,id_concesionaria)
VALUES(@idSorteo, @idConcesionaria);