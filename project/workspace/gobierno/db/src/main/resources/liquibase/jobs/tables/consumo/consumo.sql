CREATE TABLE consumo (
    id                      BIGINT          IDENTITY
    , id_concesionaria      BIGINT          NOT NULL
    , id_job_consumo        BIGINT          NOT NULL
    , offset                DATETIME        NULL
    , estado                VARCHAR(100)    NOT NULL
    , description           VARCHAR(100)    NOT NULL
    , id_request_resp       VARCHAR(100)    NULL
    , PRIMARY KEY(id_concesionaria, id_job_consumo)
    , FOREIGN KEY(id_concesionaria) REFERENCES concesionaria(id) -- codigo should not be null {aprobada}
    , FOREIGN KEY(id_job_consumo)   REFERENCES job_consumo(id)
    , FOREIGN KEY(estado)           REFERENCES estado_consumo(estado)
    , UNIQUE(id_request_resp)
);