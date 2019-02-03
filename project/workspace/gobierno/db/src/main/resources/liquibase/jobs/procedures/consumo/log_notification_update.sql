CREATE PROCEDURE log_notification_update (
    @plan_id                 BIGINT
    , @plan_estado           VARCHAR(100)
    , @plan_fecha_alta       DATETIME
    , @plan_fecha_ultima_actualizacion   DATETIME
    , @plan_tipo_de_plan     VARCHAR(100)
    , @cuota_nro_cuota       BIGINT
    , @cuota_fecha_vencimiento   DATETIME
    , @cuota_monto           INT
    , @cuota_fecha_pago      DATETIME
    , @cuota_fecha_alta      DATETIME
    , @cliente_documento     BIGINT
    , @cliente_nombre        VARCHAR(100)
    , @cliente_apellido      VARCHAR(100)
    , @cliente_nro_telefono  VARCHAR(100)
    , @cliente_email         VARCHAR(100)
    , @vehiculo_id           BIGINT
    , @vehiculo_tipo         VARCHAR(100)
    , @vehiculo_nombre       VARCHAR(100)
    , @vehiculo_precio       BIGINT
    , @vehiculo_marca        VARCHAR(100)
    , @vehiculo_modelo       VARCHAR(100)
    , @vehiculo_color        VARCHAR(100)
    , @concesionaria_id      BIGINT
) AS
INSERT INTO notification_update(plan_id,plan_estado,plan_fecha_alta,plan_fecha_ultima_actualizacion,plan_tipo_de_plan,cuota_nro_cuota,cuota_fecha_vencimiento,cuota_monto,cuota_fecha_pago,cuota_fecha_alta,cliente_documento,cliente_nombre,cliente_apellido,cliente_nro_telefono,cliente_email,vehiculo_id,vehiculo_tipo,vehiculo_nombre,vehiculo_precio,vehiculo_marca,vehiculo_modelo,vehiculo_color,concesionaria_id)
VALUES(@plan_id,@plan_estado,@plan_fecha_alta,@plan_fecha_ultima_actualizacion,@plan_tipo_de_plan,@cuota_nro_cuota,@cuota_fecha_vencimiento,@cuota_monto,@cuota_fecha_pago,@cuota_fecha_alta,@cliente_documento,@cliente_nombre,@cliente_apellido,@cliente_nro_telefono,@cliente_email,@vehiculo_id,@vehiculo_tipo,@vehiculo_nombre,@vehiculo_precio,@vehiculo_marca,@vehiculo_modelo,@vehiculo_color,@concesionaria_id);