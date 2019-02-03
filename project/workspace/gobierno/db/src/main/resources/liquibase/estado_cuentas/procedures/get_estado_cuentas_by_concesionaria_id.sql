CREATE PROCEDURE get_estado_cuentas_by_concesionaria_id (
    @concesionaria_id BIGINT
)AS
SELECT *
FROM estado_cuentas
WHERE concesionaria = @concesionaria_id;
