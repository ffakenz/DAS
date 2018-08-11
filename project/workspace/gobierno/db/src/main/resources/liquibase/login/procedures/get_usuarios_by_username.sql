CREATE PROCEDURE get_usuarios_by_username(@username VARCHAR(100))  AS
SELECT *
FROM usuario
WHERE username = @username;