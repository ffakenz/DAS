CREATE PROCEDURE update_cliente (
    @id BIGINT
    , @nro_telefono VARCHAR(20)
    , @email VARCHAR(50)
) AS
UPDATE clientes
SET nro_telefono = @nro_telefono
    , email = @email
WHERE id = @id