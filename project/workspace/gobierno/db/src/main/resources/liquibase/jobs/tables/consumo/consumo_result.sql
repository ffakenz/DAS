CREATE TABLE consumo_result (
    id                  BIGINT          NOT NULL  IDENTITY
    , id_consumo        BIGINT          NOT NULL
    , id_concesionaria  BIGINT          NOT NULL
    , result            VARCHAR(100)    NOT NULL
    , description       VARCHAR(8000)   NOT NULL
    , PRIMARY KEY(id)
    , FOREIGN KEY(id_concesionaria, id_consumo) REFERENCES consumo(id_concesionaria, id_job_consumo)
    , FOREIGN KEY(result)                       REFERENCES tipo_consumo_result(tipo)
);