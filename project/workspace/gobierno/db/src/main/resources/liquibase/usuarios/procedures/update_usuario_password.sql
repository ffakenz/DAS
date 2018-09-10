CREATE PROCEDURE update_usuario_password(
    @username VARCHAR(100)
    , @password VARCHAR(100)
) AS
UPDATE usuario
SET password = @password
WHERE username = @username;

