CREATE PROCEDURE log_consumo (
    @id_concesionaria        BIGINT
    , @id_job_consumo        BIGINT
    , @offset                DATETIME
    , @estado                VARCHAR(100)
    , @description           VARCHAR(100)
    , @id_request_resp       VARCHAR(100)
) AS
INSERT INTO consumo(id_concesionaria, id_job_consumo, offset, estado, description, id_request_resp)
VALUES(@id_concesionaria, @id_job_consumo, @offset, @estado, @description, @id_request_resp);