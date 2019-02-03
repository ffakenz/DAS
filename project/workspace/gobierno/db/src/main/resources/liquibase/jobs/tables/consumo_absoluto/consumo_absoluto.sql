CREATE TABLE consumo_absoluto (
    id                  BIGINT          NOT NULL IDENTITY
    , fecha             DATETIME        NOT NULL
    , concesionaria_id  BIGINT          NULL
    , plan_id           BIGINT          NULL
    , estado_cuenta_id  BIGINT          NULL
    , id_request_resp   VARCHAR(100)    NULL
    , estado            VARCHAR(100)    NOT NULL
    , cause             VARCHAR(1000)   NOT NULL
    , PRIMARY KEY(id)
);