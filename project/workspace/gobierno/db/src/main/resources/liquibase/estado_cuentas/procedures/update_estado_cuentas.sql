CREATE PROCEDURE update_estado_cuentas(@id BIGINT
                                           , @estado VARCHAR(30)) AS
UPDATE estado_cuentas
SET  fecha_ultima_actualizacion  = GETDATE()
     , estado = @estado
WHERE id = @id;



