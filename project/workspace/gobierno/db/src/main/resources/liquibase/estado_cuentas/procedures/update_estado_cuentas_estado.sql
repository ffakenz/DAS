CREATE PROCEDURE update_estado_cuentas_estado (
    @id BIGINT
    , @estado VARCHAR(30)
) AS
UPDATE estado_cuentas
SET estado = @estado
    , fecha_ultima_actualizacion  = SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time'
WHERE id = @id;


