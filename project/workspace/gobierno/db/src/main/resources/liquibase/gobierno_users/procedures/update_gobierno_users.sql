CREATE PROCEDURE update_gobierno_users (
    @documento BIGINT
    , @nro_telefono VARCHAR(20) = NULL
    , @email VARCHAR(50) = NULL
) AS
UPDATE gobierno_users
SET nro_telefono = @nro_telefono
    , email = @email
WHERE documento = @documento;