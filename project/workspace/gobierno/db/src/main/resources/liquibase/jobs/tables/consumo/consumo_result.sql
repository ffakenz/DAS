CREATE TABLE consumo_result (
    id_consumo          BIGINT          NOT NULL
    , id_concesionaria  BIGINT          NOT NULL
    , description       VARCHAR(100)    NOT NULL
    , result            VARCHAR(100)    NOT NULL
    , PRIMARY KEY(id_consumo, id_concesionaria)
    , FOREIGN KEY(id_consumo, id_concesionaria) REFERENCES consumo(id_concesionaria, id_job_consumo)
    , FOREIGN KEY(result)                       REFERENCES tipo_consumo_result(tipo)
);