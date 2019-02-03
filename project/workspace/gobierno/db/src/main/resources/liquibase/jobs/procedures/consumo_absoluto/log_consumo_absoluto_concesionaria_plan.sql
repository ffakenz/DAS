CREATE PROCEDURE log_consumo_absoluto_concesionaria_plan (
    @concesionaria_id   BIGINT          NULL
    , @id_request_resp  VARCHAR(100)    NULL
    , @plan_id          BIGINT          NULL
    , @estado_cuenta_id BIGINT          NULL
    , @fecha            DATETIME
    , @estado           VARCHAR(100)
    , @cause            VARCHAR(1000)
) AS
INSERT INTO consumo_absoluto(concesionaria_id, plan_id, estado_cuenta_id, id_request_resp, fecha, estado, cause)
VALUES(@concesionaria_id, @plan_id, @estado_cuenta_id, @id_request_resp, @fecha, @estado, @cause);