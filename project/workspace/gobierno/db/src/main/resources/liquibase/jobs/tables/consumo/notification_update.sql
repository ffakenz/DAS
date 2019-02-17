CREATE TABLE notification_update (
    id                      BIGINT                  NOT NULL    IDENTITY
    , plan_id               BIGINT                  NULL
    , plan_estado           VARCHAR(100)            NULL
    , plan_fecha_alta       DATETIME                NULL
    , plan_fecha_ultima_actualizacion   DATETIME    NULL
    , plan_tipo_de_plan     VARCHAR(100)            NULL
    , cuota_nro_cuota       BIGINT                  NULL
    , cuota_fecha_vencimiento   DATETIME            NULL
    , cuota_monto           INT                     NULL
    , cuota_fecha_pago      DATETIME                NULL
    , cuota_fecha_alta      DATETIME                NULL
    , cliente_documento     BIGINT                  NULL
    , cliente_nombre        VARCHAR(100)            NULL
    , cliente_apellido      VARCHAR(100)            NULL
    , cliente_nro_telefono  VARCHAR(100)            NULL
    , cliente_email         VARCHAR(100)            NULL
    , vehiculo_id           BIGINT                  NULL
    , vehiculo_tipo         VARCHAR(100)            NULL
    , vehiculo_nombre       VARCHAR(100)            NULL
    , vehiculo_precio       BIGINT                  NULL
    , vehiculo_marca        VARCHAR(100)            NULL
    , vehiculo_modelo       VARCHAR(100)            NULL
    , vehiculo_color        VARCHAR(100)            NULL
    , concesionaria_id      BIGINT                  NULL
    , id_job_consumo        BIGINT                  NULL
    , nro_consumo_result    BIGINT                  NULL
    , PRIMARY KEY(id)
    , FOREIGN KEY(id_job_consumo, concesionaria_id, nro_consumo_result)
        REFERENCES consumo_result(id_consumo, id_concesionaria, nro)
);
