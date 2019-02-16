CREATE PROCEDURE get_cliente (
    @documento      BIGINT
)
AS
SELECT TOP 1 *
FROM clientes
WHERE documento = @documento