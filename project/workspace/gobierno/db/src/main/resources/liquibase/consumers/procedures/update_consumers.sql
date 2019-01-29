CREATE PROCEDURE update_consumers (
    @documento BIGINT
    , @nro_telefono VARCHAR(20) = NULL
    , @email VARCHAR(50) = NULL
) AS
UPDATE consumers
SET nro_telefono = @nro_telefono
    , email = @email
WHERE documento = @documento;