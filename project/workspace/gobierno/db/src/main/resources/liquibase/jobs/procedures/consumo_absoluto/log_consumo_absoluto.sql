CREATE PROCEDURE log_consumo_absoluto (
    @idSorteo         BIGINT
    , @fecha          DATETIME
    , @estado         VARCHAR(100)
    , @cause          VARCHAR(8000)
) AS
INSERT INTO consumo_absoluto(id_sorteo, fecha, estado, cause)
VALUES(@idSorteo, @fecha, @estado, @cause);
