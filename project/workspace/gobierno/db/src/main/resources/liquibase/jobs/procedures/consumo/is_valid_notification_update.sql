CREATE PROCEDURE is_valid_notification_update (
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
SELECT TOP 1 *
FROM notification_update
WHERE
    plan_id = @plan_id
    AND plan_estado = @plan_estado
    AND plan_fecha_alta = @plan_fecha_alta
    AND plan_fecha_ultima_actualizacion = @plan_fecha_ultima_actualizacion
    AND plan_tipo_de_plan = @plan_tipo_de_plan
    AND cuota_nro_cuota = @cuota_nro_cuota
    AND cuota_fecha_vencimiento = @cuota_fecha_vencimiento
    AND cuota_monto = @cuota_monto
    AND cuota_fecha_pago = @cuota_fecha_pago
    AND cuota_fecha_alta = @cuota_fecha_alta
    AND cliente_documento = @cliente_documento
    AND cliente_nombre = @cliente_nombre
    AND cliente_apellido = @cliente_apellido
    AND cliente_nro_telefono = @cliente_nro_telefono
    AND cliente_email = @cliente_email
    AND vehiculo_id = @vehiculo_id
    AND vehiculo_tipo = @vehiculo_tipo
    AND vehiculo_nombre = @vehiculo_nombre
    AND vehiculo_precio = @vehiculo_precio
    AND vehiculo_marca = @vehiculo_marca
    AND vehiculo_modelo = @vehiculo_modelo
    AND vehiculo_color = @vehiculo_color
    AND concesionaria_id = @concesionaria_id;
