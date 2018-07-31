CREATE TABLE estado_cuentas (
    id BIGINT IDENTITY
    , concesionaria BIGINT NOT NULL
    , nro_plan_concesionaria BIGINT NOT NULL
    , documento_cliente BIGINT NOT NULL
    , vehiculo BIGINT NOT NULL
    , fecha_alta_concesionaria DATETIME NOT NULL
    , fecha_alta_sistema DATETIME NOT NULL DEFAULT GETDATE()
    , fecha_ultima_actualizacion DATETIME NOT NULL DEFAULT GETDATE()
    , estado VARCHAR(30) NOT NULL
    , PRIMARY KEY(id)
    , FOREIGN KEY(concesionaria) REFERENCES concesionaria(id)
    , FOREIGN KEY(estado) REFERENCES tipos_estado_cuentas(tipo)
    -- , FOREIGN KEY(cliente, concesionaria) REFERENCES clientes(correlativo, id_concesionaria)
    -- , FOREIGN KEY(tipo_vehiculo, nombre_vehiculo, marca_vehiculo, modelo_vehiculo, color_vehiculo, precio_vehiculo) REFERENCES vehiculos(tipo, nombre, marca, modelo, color, precio)
    , UNIQUE(concesionaria, nro_plan_concesionaria)
);