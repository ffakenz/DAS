CREATE PROCEDURE update_usuario(
    @documento BIGINT
    , @username VARCHAR(100)
    , @password VARCHAR(100)
) AS
UPDATE usuario
SET password = @password
    , username = @username
WHERE documento = @documento;

