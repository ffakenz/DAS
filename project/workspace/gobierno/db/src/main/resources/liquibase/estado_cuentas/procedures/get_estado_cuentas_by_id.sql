CREATE PROCEDURE get_estado_cuentas_by_id (
    @id BIGINT
)AS
SELECT *
FROM estado_cuentas
WHERE id = @id;
