CREATE PROCEDURE get_usuarios_by_username_password(@username VARCHAR(100), @password VARCHAR(100))  AS
SELECT *
FROM usuario
WHERE username = @username
    AND password = @password;