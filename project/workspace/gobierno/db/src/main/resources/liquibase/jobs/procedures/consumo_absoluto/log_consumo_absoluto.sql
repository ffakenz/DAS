CREATE PROCEDURE log_consumo_absoluto (
    @fecha            DATETIME
    , @estado         VARCHAR(100)
    , @cause          VARCHAR(1000)
) AS
INSERT INTO consumo_absoluto(fecha, estado, cause)
VALUES(@fecha, @estado, @cause);
