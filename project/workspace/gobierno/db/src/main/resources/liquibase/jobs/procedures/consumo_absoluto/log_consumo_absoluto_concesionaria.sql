CREATE PROCEDURE log_consumo_absoluto_concesionaria (
    @concesionaria_id   BIGINT          NULL
    , @fecha            DATETIME
    , @estado           VARCHAR(100)
    , @cause            VARCHAR(1000)
) AS
INSERT INTO consumo_absoluto(concesionaria_id, fecha, estado, cause)
VALUES(@concesionaria_id, @fecha, @estado, @cause);