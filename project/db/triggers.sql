USE das_final_gobierno;
GO
DROP TRIGGER validate_pendientes;
GO
CREATE TRIGGER validate_pendientes
ON sorteos
FOR INSERT
AS
BEGIN
SET NOCOUNT ON
	IF EXISTS(SELECT 1 FROM sorteos WHERE estado = 'pendiente')
	BEGIN
		ROLLBACK;
		RAISERROR(15600,-1,-1, 'no se puede registrar sorteos habiendo pendientes');
	END				
END;

GO
DROP TRIGGER validate_dia_habil;
GO
CREATE TRIGGER validate_dia_habil
ON sorteos
FOR INSERT, UPDATE
AS
BEGIN
SET NOCOUNT ON
	DECLARE @givenDate DATE;
	SELECT @givenDate = fecha FROM inserted;

	IF NOT EXISTS(SELECT 1 WHERE DATEPART(WEEKDAY, @givenDate) BETWEEN 2 AND 6)
	BEGIN
		ROLLBACK
		RAISERROR(15600,-1,-1, 'los sorteos deben registrarse un dia habil')
	END
END;

GO
DROP TRIGGER validate_unico_sorteo_mensual;
GO
CREATE TRIGGER validate_unico_sorteo_mensual
ON sorteos
FOR INSERT, UPDATE
AS
BEGIN
SET NOCOUNT ON
	DECLARE @givenDate DATE;
	SELECT @givenDate = fecha FROM inserted;

	IF EXISTS(
		SELECT 1 
		FROM sorteos 
		WHERE DATEPART(MONTH, fecha) = DATEPART(MONTH, @givenDate)
		AND estado = 'nuevo'
		AND id NOT IN (SELECT id FROM inserted)
	)
	BEGIN
		ROLLBACK
		RAISERROR(15600,-1,-1, 'no puede haber mas de un sorteo nuevo por mes')
	END
END;