CREATE PROCEDURE get_usuarios(@username VARCHAR(100), @password VARCHAR(100))  AS
SELECT *
FROM usuario
WHERE username = @username
    AND password = @password;