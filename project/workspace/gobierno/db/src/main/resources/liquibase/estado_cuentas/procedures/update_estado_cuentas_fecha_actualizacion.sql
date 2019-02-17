CREATE PROCEDURE update_estado_cuentas_fecha_actualizacion (
    @id BIGINT
    , @fechaUltimaActualizacion DATETIME
) AS
UPDATE estado_cuentas
SET fecha_ultima_actualizacion = @fechaUltimaActualizacion
WHERE id = @id;


