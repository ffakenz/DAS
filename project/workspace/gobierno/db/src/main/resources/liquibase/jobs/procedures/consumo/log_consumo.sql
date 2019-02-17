CREATE PROCEDURE log_consumo (
    @id_concesionaria        BIGINT
    , @id_job_consumo        BIGINT
    , @from_offset           DATETIME
    , @to_offset             DATETIME
    , @estado                VARCHAR(100)
    , @description           VARCHAR(8000)
    , @id_request_resp       VARCHAR(100)
) AS
INSERT INTO consumo(id_concesionaria, id_job_consumo, "from", "to", estado, description, id_request_resp)
VALUES(@id_concesionaria, @id_job_consumo, @from_offset, @to_offset, @estado, @description, @id_request_resp);