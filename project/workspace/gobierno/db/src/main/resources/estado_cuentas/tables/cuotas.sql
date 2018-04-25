CREATE TABLE cuotas (
    estado_cuenta_id BIGINT NOT NULL
    , id BIGINT IDENTITY NOT NULL
    , fecha_vencimiento DATETIME NOT NULL
    , monto INT NULL
    , fecha_pago DATETIME NULL
    , PRIMARY KEY(estado_cuenta_id, id)
    , FOREIGN KEY(estado_cuenta_id) REFERENCES estado_cuentas(id)
);