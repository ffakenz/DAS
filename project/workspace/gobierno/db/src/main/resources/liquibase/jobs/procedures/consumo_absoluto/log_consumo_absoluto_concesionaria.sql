CREATE PROCEDURE log_consumo_absoluto_concesionaria (
    @idSorteo             BIGINT
    , @concesionaria_id   BIGINT          NULL
    , @fecha              DATETIME
    , @estado             VARCHAR(100)
    , @cause              VARCHAR(8000)
) AS
INSERT INTO consumo_absoluto(id_sorteo, concesionaria_id, fecha, estado, cause)
VALUES(@idSorteo, @concesionaria_id, @fecha, @estado, @cause);