CREATE PROCEDURE insert_usuario(
    @username VARCHAR(100)
    , @password VARCHAR(100)
    , @rol VARCHAR(100)
) AS
INSERT INTO usuario(username, password, rol)
VALUES (@username, @password, @rol);