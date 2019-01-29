CREATE PROCEDURE insert_usuario(
    @documento BIGINT
    , @username VARCHAR(100)
    , @password VARCHAR(100)
    , @rol VARCHAR(100)
) AS
INSERT INTO usuario(documento, username, password, rol)
VALUES (@documento, @username, @password, @rol);