USE db_gobierno;

GO 
DROP PROCEDURE get_usuarios;
GO CREATE PROCEDURE get_usuarios AS SELECT * FROM usuarios;

-- unit test above
EXEC get_usuarios;