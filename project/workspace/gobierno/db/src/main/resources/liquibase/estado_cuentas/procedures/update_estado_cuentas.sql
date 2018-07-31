CREATE PROCEDURE update_estado_cuentas(@id BIGINT
                                           , @concesionaria BIGINT
                                           , @nro_plan_concesionaria BIGINT
                                           , @documento_cliente BIGINT
                                           , @vehiculo BIGINT
                                           , @fecha_alta_concesionaria DATETIME
                                           , @fecha_alta_sistema DATETIME
                                           , @fecha_ultima_actualizacion DATETIME) AS
UPDATE estado_cuentas
SET  = @
WHERE id = @id;