CREATE PROCEDURE update_usuario_password(
    @documento BIGINT
    , @password VARCHAR(100)
) AS
UPDATE usuario
SET password = @password
WHERE documento = @documento;

