CREATE TABLE cuotas (
    id                  BIGINT              IDENTITY
    , nro_cuota         BIGINT      NULL    -- inserted by a trigger
    , estado_cuenta_id  BIGINT      NOT NULL
    , fecha_vencimiento DATETIME    NOT NULL
    , monto             INT         NULL
    , fecha_pago        DATETIME    NULL
    , PRIMARY KEY(estado_cuenta_id, id)
    , FOREIGN KEY(estado_cuenta_id) REFERENCES estado_cuentas(id)
    , UNIQUE (estado_cuenta_id, nro_cuota)
);