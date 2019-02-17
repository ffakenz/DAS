CREATE TABLE estado_cuentas (
    id BIGINT IDENTITY
    , concesionaria BIGINT NOT NULL
    , nro_plan_concesionaria BIGINT NOT NULL
    , dni_consumer BIGINT NOT NULL
    , vehiculo BIGINT NOT NULL
    , fecha_alta_concesionaria DATETIME NOT NULL
    , fecha_alta_sistema DATETIME NOT NULL DEFAULT GETDATE()
    , fecha_ultima_actualizacion DATETIME NOT NULL DEFAULT GETDATE()
    , estado VARCHAR(30) NOT NULL -- cant_cuotas_pagadas <- trigger ??
    , PRIMARY KEY(id)
    , FOREIGN KEY(estado)                      REFERENCES tipos_estado_cuentas(tipo)
    , FOREIGN KEY(dni_consumer)                REFERENCES consumers(documento)
    , FOREIGN KEY(concesionaria)               REFERENCES concesionaria(id)
    , FOREIGN KEY(vehiculo)                    REFERENCES vehiculos(id)
    -- , FOREIGN KEY(tipo_vehiculo, nombre_vehiculo, marca_vehiculo, modelo_vehiculo, color_vehiculo, precio_vehiculo) REFERENCES vehiculos(tipo, nombre, marca, modelo, color, precio)
    , UNIQUE(concesionaria, nro_plan_concesionaria) -- TODO: is this scenario possible ?
);