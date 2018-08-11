CREATE PROCEDURE delete_usuario(@username VARCHAR(100)) AS
DELETE FROM usuario WHERE username = @username;