CREATE PROCEDURE log_consumo_result (
    @id_concesionaria        BIGINT
    , @id_consumo            BIGINT
    , @nro                   BIGINT
    , @description           VARCHAR(8000)
    , @result                VARCHAR(100)
) AS
INSERT INTO consumo_result(id_concesionaria, nro, id_consumo, description, result)
VALUES(@id_concesionaria, @nro, @id_consumo, @description, @result);