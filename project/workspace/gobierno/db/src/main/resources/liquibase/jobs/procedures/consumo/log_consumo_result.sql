CREATE PROCEDURE log_consumo_result (
    @id_concesionaria        BIGINT
    , @id_consumo            BIGINT
    , @description           VARCHAR(100)
    , @result                VARCHAR(100)
) AS
INSERT INTO consumo_result(id_concesionaria, id_consumo, description, result)
VALUES(@id_concesionaria, @id_consumo, @description, @result);