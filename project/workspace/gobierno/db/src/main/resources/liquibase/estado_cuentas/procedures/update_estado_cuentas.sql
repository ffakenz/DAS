CREATE PROCEDURE update_estado_cuentas (
    @concesionaria BIGINT
    , @nro_plan_concesionaria BIGINT
    , @estado VARCHAR(30)
) AS
UPDATE estado_cuentas
SET estado = @estado
    , fecha_ultima_actualizacion  = SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time'
WHERE concesionaria = @concesionaria
    AND nro_plan_concesionaria = @nro_plan_concesionaria;



