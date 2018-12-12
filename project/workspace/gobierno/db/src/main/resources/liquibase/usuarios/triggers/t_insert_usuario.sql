CREATE TRIGGER t_insert_usuario ON usuario
FOR INSERT
AS
BEGIN
  DECLARE @username_i VARCHAR(100)
  SELECT @username_i = username FROM inserted

  INSERT INTO usuario(documento, username, password, rol)

END
GO