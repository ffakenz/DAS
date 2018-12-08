CREATE TABLE job_consumo (
    id      BIGINT      NOT NULL IDENTITY
    , fecha DATETIME    NOT NULL DEFAULT     GETDATE()
    , PRIMARY KEY(id)
);