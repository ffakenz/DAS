USE db_gobierno;

GO
DROP PROCEDURE get_usuarios;
GO
CREATE PROCEDURE get_usuarios  AS SELECT * FROM usuario;
-- unit test above
EXEC get_usuarios;

GO
DROP PROCEDURE get_logins;
GO
CREATE PROCEDURE get_logins  AS SELECT * FROM login;
-- unit test above
EXEC get_logins;

GO
DROP PROCEDURE log_login;
GO
CREATE PROCEDURE log_login(@tipo VARCHAR(100), @username VARCHAR(100))  AS 
INSERT INTO login(tipo, username)
VALUES (@tipo, @username);
-- unit test above
EXEC log_login 'gobierno', 'ffakenz';




SELECT du.*
FROM usuarios u
    join detalle_usuario du
        on u.rol = du.rol
        and u.username = du.username;