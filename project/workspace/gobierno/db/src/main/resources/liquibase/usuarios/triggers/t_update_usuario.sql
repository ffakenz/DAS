CREATE TRIGGER t_update_usuario ON usuario
FOR UPDATE
AS BEGIN

  IF update(documento) or update(rol)
  BEGIN
    raiserror('Actualización no válida', 10, 1)
    ROLLBACK TRANSACTION
  END

  DECLARE @username_i VARCHAR(100)
  SELECT @username_i = username FROM inserted

  DECLARE @cant_usuarios BIGINT
  SELECT @cant_usuarios = count(*) FROM usuario WHERE username = @username_i

  IF @cant_usuarios > 1
  BEGIN
    PRINT @username_i
    PRINT @cant_usuarios
    raiserror('Nombre de usuario existente', 10, 1)
    ROLLBACK TRANSACTION
  END
END