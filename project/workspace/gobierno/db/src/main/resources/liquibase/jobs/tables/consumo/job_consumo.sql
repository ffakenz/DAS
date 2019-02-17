CREATE TABLE job_consumo (
    id      BIGINT      NOT NULL IDENTITY
    , fecha DATETIME    NOT NULL DEFAULT     SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time'
    , PRIMARY KEY(id)
);