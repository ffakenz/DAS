CREATE PROCEDURE update_estado_cuentas (
    @concesionaria BIGINT
    , @nro_plan_concesionaria BIGINT
    , @estado VARCHAR(30)
) AS
UPDATE estado_cuentas
SET  fecha_ultima_actualizacion  = GETDATE()
     , estado = @estado
WHERE concesionaria = @concesionaria
    AND nro_plan_concesionaria = @nro_plan_concesionaria;



