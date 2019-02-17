CREATE TABLE cuotas (
    nro_cuota                    BIGINT      NOT NULL    -- inserted by a trigger -> {not any more}
    , estado_cuenta_id           BIGINT      NOT NULL
    , fecha_alta_concesionaria   DATETIME    NOT NULL
    , fecha_vencimiento          DATETIME    NOT NULL
    , monto                      INT         NULL
    , fecha_pago                 DATETIME    NULL
    , fecha_ultima_actualizacion DATETIME    NOT NULL DEFAULT SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time'
    , PRIMARY KEY(nro_cuota, estado_cuenta_id)
    , FOREIGN KEY(estado_cuenta_id) REFERENCES estado_cuentas(id)
);