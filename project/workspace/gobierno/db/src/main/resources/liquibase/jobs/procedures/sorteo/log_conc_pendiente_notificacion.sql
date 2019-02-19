CREATE PROCEDURE log_conc_pendiente_notificacion (
    @idSorteo BIGINT
    , @idConcesionaria BIGINT
) AS
INSERT INTO conc_pendiente_notificacion(id_sorteo,id_concesionaria)
VALUES(@idSorteo, @idConcesionaria);