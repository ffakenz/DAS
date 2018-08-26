CREATE PROCEDURE get_estado_cuentas_by_nro_plan_and_concesionaria (
    @concesionaria BIGINT
    , @nro_plan_concesionaria BIGINT
)AS
SELECT *
FROM estado_cuentas
WHERE concesionaria = @concesionaria
    AND nro_plan_concesionaria = @nro_plan_concesionaria;
